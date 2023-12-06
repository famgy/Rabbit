package com.feilinpl.rabbit.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBox(
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