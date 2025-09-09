package com.wink.app.winkapptest.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wink.app.domain.model.Photo
import com.wink.app.winkapptest.ui.utils.NetworkImage

@Composable
fun BottomPhoto(photo: Photo, maxLines: Int = 1) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(CircleShape)
            .size(48.dp)
    ) {
        NetworkImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape),
            url = photo.authorImageUrl,
            contentDescription = photo.description,
            contentScale = ContentScale.Crop
        )
    }
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = photo.author,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = photo.description,
            style = MaterialTheme.typography.bodySmall,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}