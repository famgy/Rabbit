package com.feilinpl.rabbit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.feilinpl.rabbit.ui.screen.AdSenseScreen
import com.feilinpl.rabbit.ui.screen.FirebaseScreen
import com.feilinpl.rabbit.ui.screen.GoogleApiTestScreen
import com.feilinpl.rabbit.ui.screen.MainScreen
import com.feilinpl.rabbit.ui.screen.SignInScreen
import com.feilinpl.rabbit.ui.screen.SystemApiTestScreen
import com.feilinpl.rabbit.ui.theme.RabbitTheme
import com.feilinpl.rabbit.viewmodel.AdSenseViewModel
import com.feilinpl.rabbit.viewmodel.FirebaseViewModel
import com.feilinpl.rabbit.viewmodel.GoogleApiTestViewModel
import com.feilinpl.rabbit.viewmodel.GoogleServicesViewModel
import com.feilinpl.rabbit.viewmodel.SignInViewModel
import com.feilinpl.rabbit.viewmodel.SystemApiTestViewModel
import com.feilinpl.rabbit.viewmodel.SystemApiTestViewModelFactory

class MainActivity : ComponentActivity() {
    private val systemApiTestViewModel: SystemApiTestViewModel by viewModels {
        SystemApiTestViewModelFactory(application)
    }

    private val googleApiTestViewModel: GoogleApiTestViewModel by viewModels()
    private val servicesViewModel: GoogleServicesViewModel by viewModels()

    private val adSenseViewModel: AdSenseViewModel by viewModels()
    private val signInViewModel: SignInViewModel by viewModels()
    private val firebaseViewModel: FirebaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RabbitTheme {
                // 使用 NavHost 进行界面导航
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {
                    // 主界面
                    composable("main") {
                        MainScreen(navController = navController, viewModel = servicesViewModel)
                    }

                    composable("google api test") {
                        GoogleApiTestScreen(navController = navController, viewModel = googleApiTestViewModel)
                    }

                    composable("system api test") {
                        SystemApiTestScreen( viewModel = systemApiTestViewModel)
                    }

                    composable("third") {
                        // Record "Screen View" event for Firebase Analytics
                        AdSenseScreen(viewModel = adSenseViewModel)
                    }

                    composable("firebase") {
                        FirebaseScreen(viewModel = firebaseViewModel)
                    }

                    composable("signIn") {
                        SignInScreen(viewModel = signInViewModel)
                    }
                }
            }
        }
    }
}