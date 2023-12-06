package com.feilinpl.rabbit.ui.screen

import androidx.activity.viewModels
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.feilinpl.rabbit.R
import com.feilinpl.rabbit.ui.component.ButtonBox
import com.feilinpl.rabbit.viewmodel.GoogleServicesViewModel


@Composable
fun MainScreen(navController: NavController, viewModel: GoogleServicesViewModel = viewModel()) {
    // 在应用启动时检查 Google Play 服务的可用性，并存储在 ViewModel 中
    viewModel.checkGooglePlayServices(LocalContext.current)
    val isGooglePlayServicesAvailable by viewModel.isGooglePlayServicesAvailable

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
                text = "Welcome to MainScreen",
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(32.dp))  // 增加垂直间距

            Text(
                text = "Google服务环境：$isGooglePlayServicesAvailable",
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))  // 增加垂直间距
        }

        // 内容
        ButtonBox(
            buttonText = "System Api Test",
            onClick = {
                navController.navigate("system api test")
            }
        )
        ButtonBox(
            buttonText = "Google Api Test",
            onClick = {
                navController.navigate("google api test")
            }
        )
    }
}

