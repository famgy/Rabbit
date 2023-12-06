package com.feilinpl.rabbit.ui.screen

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.feilinpl.rabbit.viewmodel.SignInViewModel
import com.feilinpl.rabbit.R
import com.feilinpl.rabbit.ui.component.ButtonBox
import com.feilinpl.rabbit.utils.GoogleSignInType
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import java.lang.Integer.min


@Composable
fun SignInScreen(viewModel: SignInViewModel) {
    val context = LocalContext.current
    val signInAccount by viewModel.signInAccount.observeAsState()
    val contact by viewModel.contact.observeAsState()

    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)

            // Login successful, update ViewModel with account information
            viewModel.updateSignInAccount(context, account)
        } else {
            Log.e("signInLauncher failed, resultCode : ", result.resultCode.toString())
        }
    }

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
                text = "Welcome to SignInScreen",
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold

            )

            Spacer(modifier = Modifier.height(32.dp))  // 增加垂直间距

            Text(
                text = signInAccount?.displayName ?: "Signed Out",
                fontSize = 14.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))  // 增加垂直间距

            if (signInAccount?.displayName != null) {
                Text(
                    text = "IdToken: ${signInAccount?.idToken?.substring(0, min(20, signInAccount!!.idToken!!.length))}",
                    fontSize = 14.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))  // 增加垂直间距

                Text(
                    text = "ServerAuthCode: ${signInAccount?.serverAuthCode?.substring(0, min(20, signInAccount!!.serverAuthCode!!.length))}",
                    fontSize = 14.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))  // 增加垂直间距

                Text(
                    text = "contact: $contact",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }

        // 根据登录状态渲染不同的按钮
        if (viewModel.signInAccount.value == null) {
            // 未登录状态，显示登录按钮
            ButtonBox(
                buttonText = "Sign In",
                onClick = {
                    viewModel.initializeGoogleSignInClient(context, GoogleSignInType.Default)

                    val signInIntent = viewModel.googleSignInClient?.value?.signInIntent
                    signInLauncher.launch(signInIntent)
                }
            )

            ButtonBox(
                buttonText = "Sign In with Token",
                onClick = {
                    viewModel.initializeGoogleSignInClient(context, GoogleSignInType.WithIdToken)

                    val signInIntent = viewModel.googleSignInClient?.value?.signInIntent
                    signInLauncher.launch(signInIntent)
                }
            )

            ButtonBox(
                buttonText = "Sign In with AuthCode",
                onClick = {
                    viewModel.initializeGoogleSignInClient(context, GoogleSignInType.ServerAuthCode)

                    val signInIntent = viewModel.googleSignInClient?.value?.signInIntent
                    signInLauncher.launch(signInIntent)
                }
            )

            ButtonBox(
                buttonText = "Sign In with Scopes",
                onClick = {
                    viewModel.initializeGoogleSignInClient(context, GoogleSignInType.WithScopes)

                    val signInIntent = viewModel.googleSignInClient?.value?.signInIntent
                    signInLauncher.launch(signInIntent)
                }
            )
        } else {
            // 登录状态，显示登出按钮
            ButtonBox(
                buttonText = "Sign Out",
                onClick = {
                    val googleSignInClient = viewModel.googleSignInClient?.value
                    googleSignInClient?.signOut()
                        ?.addOnCompleteListener {
                            viewModel.updateSignInAccount(context, null)
                        };
                }
            )
        }
    }
}


