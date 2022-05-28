package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import de.fhe.ai.pmc.acat.app.ui.screens.util.PREVIEW_BACKGROUND_COLOR
import de.fhe.ai.pmc.acat.app.ui.screens.util.SessionListPreviewParameterProvider
import de.fhe.ai.pmc.acat.domain.Session

@Composable
fun SessionList(
    sessions: List<Session>,
    loading: Boolean,
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {},
    onItemPressed: ( itemId: String ) -> Unit
) {
    val scrollState = rememberLazyListState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(loading),
        onRefresh = onRefresh,
    ) {
        LazyColumn(state = scrollState, modifier = modifier) {
            items(sessions) {
                SessionRow( it, modifier = modifier, onItemPressed )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = PREVIEW_BACKGROUND_COLOR
)
@Composable
fun PreviewSessionList(
    @PreviewParameter(SessionListPreviewParameterProvider::class) sessions: List<Session>
) {
    SessionList( sessions, false ) {}
}
