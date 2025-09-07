package com.wink.app.winkapptest.ui.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wink.app.domain.model.Photo

@Composable
fun ListItem(
    photo: Photo,
    onClick: () -> Unit
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier =
                Modifier.clickable { onClick() }
        ) {
            AsyncImage(
                model = photo.imageUrl,
                contentDescription = photo.description
            )
            Text(
                text = photo.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}