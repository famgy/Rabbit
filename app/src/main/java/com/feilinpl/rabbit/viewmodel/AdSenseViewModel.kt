package com.feilinpl.rabbit.viewmodel

import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdRequest

class AdSenseViewModel : ViewModel() {
    fun loadAd(adRequest: AdRequest, adUnitId: String): AdRequest {
        // 加载 AdSense 广告
        // 这里需要根据你的具体需求选择 AdView、AdManagerAdView、SearchAdView 或 DynamicHeightSearchAdView
        // 并配置相应的参数
        return adRequest
    }
}