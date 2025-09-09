package com.wink.app.winkapptest.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wink.app.winkapptest.R
import com.wink.app.winkapptest.ui.theme.Container
import com.wink.app.winkapptest.ui.theme.Gray

@Composable
fun ImageSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(50))
            .background(Container)
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            textStyle = TextStyle(
                color = Gray,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 50.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchQueryChanged(query) }
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_images),
                            color = Gray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    innerTextField()
                }
            }
        )

        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
                .size(24.dp)
                .clickable { onSearchQueryChanged(query) }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ImageSearchBarPreview() {
    ImageSearchBar(
        "Test", {}
    ) {}
}