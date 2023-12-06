package com.feilinpl.rabbit.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.feilinpl.rabbit.utils.GooglePlayServicesUtils

class GoogleServicesViewModel : ViewModel() {

    // Google Play 服务的可用性
    private val _isGooglePlayServicesAvailable = mutableStateOf(false)
    val isGooglePlayServicesAvailable: State<Boolean> get() = _isGooglePlayServicesAvailable

    fun checkGooglePlayServices(context: Context) {
        // 执行 Google Play 服务的检查逻辑，更新 _isGooglePlayServicesAvailable
        _isGooglePlayServicesAvailable.value = GooglePlayServicesUtils.isGooglePlayServicesAvailable(context)
    }
}