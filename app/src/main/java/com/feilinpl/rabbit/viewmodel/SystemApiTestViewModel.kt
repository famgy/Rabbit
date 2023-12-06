package com.feilinpl.rabbit.viewmodel

import android.app.Application
import android.content.Context
import android.media.AudioManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SystemApiTestViewModel(application: Application) : AndroidViewModel(application) {
    private val _result = MutableLiveData<String?>()
    val result: LiveData<String?> get() = _result

    private val audioManager: AudioManager = application.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    fun testAudioManager() {
        try {
            // 请求音频焦点
            val result = audioManager.requestAudioFocus(
                null, // 侦听器为 null
                AudioManager.STREAM_MUSIC, // 音频流类型
                AudioManager.AUDIOFOCUS_GAIN // 请求永久焦点
            )

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // 请求成功, 然后释放音频焦点
                val abandonResult = audioManager.abandonAudioFocus(null)

                if (abandonResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // 放弃成功
                    _result.value = "Audio Focus Abandoned"
                } else {
                    // 放弃失败
                    _result.value = "Failed to abandon Audio Focus"
                }
            } else {
                // 请求失败
                _result.value = "Audio Focus Not Granted"
            }
        } catch (e: SecurityException) {
            // 捕捉安全异常
            _result.value = "SecurityException"
        }
    }
}

class SystemApiTestViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(SystemApiTestViewModel::class.java)) {
            "Unknown ViewModel class: ${modelClass.name}"
        }
        return SystemApiTestViewModel(application) as T
    }
}