package de.fhe.ai.pmc.acat.app.ui.screens.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.domain.AsyncOperation

@Composable
fun AsyncPlaceholderView(asyncOperation: AsyncOperation) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 12.dp)
            .align(Alignment.Center)) {
            Icon(
                Icons.Filled.Info,
                asyncOperation.message,
                tint = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .scale(3f)
                    .padding(40.dp)
            )
            Text(asyncOperation.message,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onBackground
            )
        }

    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND_COLOR
)
@Composable
fun PreviewUserRow(
    @PreviewParameter(AsyncOpPreviewParameterProvider::class) asyncOperation: AsyncOperation
) {
    AsyncPlaceholderView(asyncOperation)
}
