package com.wink.app.winkapptest.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.wink.app.winkapptest.R

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String,
    contentScale: ContentScale
) {
    val isInPreview = LocalInspectionMode.current
    val context = LocalContext.current

    val imageRequest: ImageRequest = remember(key1 = url) {
        ImageRequest.Builder(context)
            .data(data = url)
            .crossfade(true)
            .placeholder(R.drawable.photo_placeholder)
            .error(R.drawable.generic_error)
            .build()
    }

    if (isInPreview) {
        Image(
            painter = painterResource(id = R.drawable.photo_placeholder),
            contentDescription = contentDescription,
            modifier = modifier
        )
    } else {
        Image(
            painter =
                rememberAsyncImagePainter(
                    model = imageRequest,
                    contentScale = contentScale
                ),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}