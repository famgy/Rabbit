package com.feilinpl.rabbit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.feilinpl.rabbit.viewmodel.AdSenseViewModel
import com.google.android.gms.ads.AdRequest

@Composable
fun AdSenseScreen(viewModel: AdSenseViewModel) {
    var searchText by remember { mutableStateOf("") }
    var isAdLoaded by remember { mutableStateOf(false) }
    val adRequest = remember { AdRequest.Builder().build() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 搜索框
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // 在搜索时加载广告
                    isAdLoaded = true
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // 广告视图
        if (isAdLoaded) {
            // 使用 AdView 进行 AdSense 横幅广告测试
//            AdView(
//                adSize = AdSize.BANNER,
//                adUnitId = "YOUR_AD_UNIT_ID", // 替换为你的广告单元 ID
//                adRequest = adSenseViewModel.loadAd(adRequest, "YOUR_AD_UNIT_ID"),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp) // 调整高度
//            )
        }
    }
}