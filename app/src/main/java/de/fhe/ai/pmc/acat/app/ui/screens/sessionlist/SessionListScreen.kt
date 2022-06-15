package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import de.fhe.ai.pmc.acat.app.ui.components.NetworkError

@Composable
fun SessionsListScreen(vm: SessionListScreenViewModel, modifier: Modifier = Modifier) {
    val sessionList by vm.sessionItems.observeAsState()
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()

    // Get sessions on first render
    if(sessionList?.isEmpty() == true && !loading){
        vm.getSessions()
    }

    val scrollState = rememberLazyListState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(loading),
        onRefresh = { vm.getSessions() },
    ) {
        Column {
            if(error.isNotBlank()){
                NetworkError(text = error)
            }

            LazyColumn(state = scrollState, modifier = modifier) {
                items(sessionList!!) { item ->
                    SessionRow( item, modifier = modifier, onItemPressed = { vm.navigateToSession(item.id) } )
                }
            }
        }
    }
}
