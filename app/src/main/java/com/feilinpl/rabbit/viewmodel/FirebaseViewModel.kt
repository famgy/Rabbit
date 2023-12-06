package com.feilinpl.rabbit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase


class FirebaseViewModel : ViewModel() {
    private val firebaseAnalytics: FirebaseAnalytics = Firebase.analytics
    private val firebaseInstallations: FirebaseInstallations = FirebaseInstallations.getInstance()

    private val _instanceId = MutableLiveData<String?>()
    val instanceId: LiveData<String?> get() = _instanceId
    private val _installationId = MutableLiveData<String?>()
    val installationId: LiveData<String?> get() = _installationId


    fun getInstanceId() {
        firebaseAnalytics.appInstanceId.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _instanceId.value = task.result
            } else {
                // 获取实例 ID 失败时的处理
                Log.e("FirebaseViewModel", "Firebase: Unable to get Instance ID")
            }
        }
    }

    fun getInstallationId() {
        firebaseInstallations.id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _installationId.value = task.result
            } else {
                Log.e("FirebaseViewModel", "Firebase: Unable to get Installation ID")
            }
        }
    }

}