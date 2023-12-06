package com.feilinpl.rabbit.ui.screen

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feilinpl.rabbit.R
import com.feilinpl.rabbit.utils.GoogleSignInType
import com.feilinpl.rabbit.viewmodel.FirebaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

@Composable
fun FirebaseScreen(viewModel: FirebaseViewModel) {
    val context = LocalContext.current
    val instanceId by viewModel.instanceId.observeAsState()
    val installationId by viewModel.installationId.observeAsState()

    // 渲染 UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF455A64))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo and Welcome Text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 128.dp),  // 添加一些底部间距
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.googleg_color),
                contentDescription = "Google Icon",
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))  // 添加一些垂直间距

            Text(
                text = "Welcome to FirebaseScreen",
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(32.dp))  // 增加垂直间距

            Text(
                text = "instanceId: ${instanceId?.substring(0, Integer.min(20, instanceId!!.length))}",
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))  // 增加垂直间距

            Text(
                text = "installationId: ${installationId?.substring(0, Integer.min(20, installationId!!.length))}",
                fontSize = 14.sp,
                color = Color.White
            )
        }

        FirebaseButtonBox(
            buttonText = "Get Instance Id",
            onClick = {
                viewModel.getInstanceId()
            }
        )

        FirebaseButtonBox(
            buttonText = "Get Installation Id",
            onClick = {
                viewModel.getInstallationId()
            }
        )
    }
}

@Composable
fun FirebaseButtonBox(
    buttonText: String,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .sizeIn(maxWidth = 200.dp, maxHeight = 48.dp)
        ) {
            Text(text = buttonText)
        }
    }
}