package com.feilinpl.rabbit.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GoogleApiTestViewModel() : ViewModel() {

    // Google 移动广告
    fun testAdMobApi(context: Context, onAdMobInitialized: () -> Unit) {
        try {
            // 初始化 AdMob
            MobileAds.initialize(context) {
                // 初始化成功的回调
                Log.d("GoogleServicesTestViewModel", "AdMob initialized successfully")

                // 在初始化成功后调用回调函数
                onAdMobInitialized.invoke()
            }
        } catch (e: Exception) {
            // 处理异常
            Log.e("GoogleServicesTestViewModel", "Error initializing AdMob: ${e.message}")
        }
    }

    // Android 广告 ID (AAID)
    fun testAdvertisingId(context: Context) {
        try {
            // 使用协程在后台线程中获取广告标识
            GlobalScope.launch(Dispatchers.IO) {
                val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)

                // 在主线程中显示 Toast
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "广告标识: ${adInfo.id}, 是否启用广告跟踪限制: ${adInfo.isLimitAdTrackingEnabled}", Toast.LENGTH_SHORT).show()
                }

                // 记录广告标识以及是否启用广告跟踪限制
                Log.d("GoogleServicesTestViewModel", "广告标识: ${adInfo.id}, 是否启用广告跟踪限制: ${adInfo.isLimitAdTrackingEnabled}")
            }
        } catch (e: Exception) {
            // 处理错误
            Log.e("GoogleServicesTestViewModel", "获取广告标识时出错: ${e.message}")
        }
    }

    // 精简版 Google 移动广告： 只能在googleplay商店上架的应用使用


    // AdSense 搜索广告 (AFS) 自定义搜索广告 (CSA)

    // Google Analytics for Firebase , Google Analytics（分析）服务 SDK

    // 应用索引

    // Android 应用组 ID

    // Android 版 Google 登录


}