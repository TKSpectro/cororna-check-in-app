package de.fhe.ai.pmc.acat.app.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.app.ui.screens.util.UserPreviewParameterProvider
import de.fhe.ai.pmc.acat.app.ui.screens.util.previewBackgroundColor
import de.fhe.ai.pmc.acat.domain.User

@Composable
fun UserRow(user: User, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = {
                // Do something
            })
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(user.name)
    }
}

@Preview(
    showBackground = true,
    backgroundColor = previewBackgroundColor
)
@Composable
fun PreviewUserRow(
    @PreviewParameter(UserPreviewParameterProvider::class, limit = 3) user: User
) {
    UserRow( user )
}