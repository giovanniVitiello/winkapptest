package com.wink.app.winkapptest.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wink.app.winkapptest.utils.data.Resource

@Composable
fun <T> ResourceContentHandler(
    resource: Resource<T>,
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit,
    composable: @Composable (T) -> Unit
) {

    when (resource) {
        is Resource.Success -> Box(modifier = modifier) {
            composable(resource.data)
        }

        is Resource.Error -> Box(modifier = modifier) {
            Error(
                error = resource.cause,
                onRetryClicked = onRetryClicked,
                modifier = Modifier.fillMaxSize(),
            )
        }

        is Resource.Loading -> {
            Box(modifier = Modifier.background(Color.Transparent)) {
                Loading(modifier = Modifier.fillMaxSize())
            }
        }

    }
}
