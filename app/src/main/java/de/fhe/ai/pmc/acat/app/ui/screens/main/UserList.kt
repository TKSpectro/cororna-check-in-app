package de.fhe.ai.pmc.acat.app.ui.screens.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import de.fhe.ai.pmc.acat.app.ui.screens.util.UserListPreviewParameterProvider
import de.fhe.ai.pmc.acat.app.ui.screens.util.previewBackgroundColor
import de.fhe.ai.pmc.acat.domain.User

@Composable
fun UserList(users: List<User>, modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState, modifier = modifier) {
        items(users) {
            UserRow(it, modifier = modifier)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = previewBackgroundColor
)
@Composable
fun PreviewUserList(
    @PreviewParameter(UserListPreviewParameterProvider::class) users: List<User>
) {
    UserList( users )
}
