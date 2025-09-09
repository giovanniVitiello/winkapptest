package com.wink.app.winkapptest.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wink.app.winkapptest.R
import com.wink.app.winkapptest.ui.theme.ErrorColor

@Composable
fun Error(
    error: Throwable?,
    onRetryClicked: () -> Unit,
    modifier: Modifier = Modifier,
    background: Color = Color.White,
    padding: PaddingValues = PaddingValues(16.dp),
) {
    Column(
        modifier =
            modifier
                .background(background)
                .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter =
                painterResource(
                    id = R.drawable.generic_error
                ),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error?.message.orEmpty(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onRetryClicked() },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.textButtonColors(
                containerColor = ErrorColor,
                contentColor = ErrorColor
            ),
            border = BorderStroke(1.dp, ErrorColor)
        ) {
            Text(
                text = stringResource(R.string.retry),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun ErrorPreview() {
    Error(
        error = Throwable(message = stringResource(R.string.error_default_message)),
        onRetryClicked = {},
        modifier = Modifier.fillMaxSize()
    )
}
