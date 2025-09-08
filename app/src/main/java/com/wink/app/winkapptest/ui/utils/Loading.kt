package com.wink.app.winkapptest.ui.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.wink.app.winkapptest.R
import com.wink.app.winkapptest.utils.ext.launchSafe
import kotlinx.coroutines.delay

@Composable
fun Loading(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    padding: PaddingValues = PaddingValues(0.dp)
) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading_dots)
    )

    val coroutineScope = rememberCoroutineScope()
    var showLoading by remember { mutableStateOf(false) }

    if (isVisible)
        Box(
            modifier =
                modifier
                    .background(Color.Transparent)
                    .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            coroutineScope.launchSafe {
                delay(500L)
                showLoading = true
            }
            if (showLoading) {
                LottieAnimation(
                    composition = composition,
                    iterations = Integer.MAX_VALUE,
                    modifier = Modifier.size(80.dp)
                )
            }
        }

}

@Preview
@Composable
private fun LoadingPreview() {
    Loading(isVisible = true, modifier = Modifier.fillMaxSize())
}
