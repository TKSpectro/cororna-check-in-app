package de.fhe.ai.pmc.acat.app.ui.screens.roomlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import de.fhe.ai.pmc.acat.app.ui.components.NetworkError
import de.fhe.ai.pmc.acat.domain.Room

@Composable
fun RoomListScreen(vm: RoomListScreenViewModel, modifier: Modifier = Modifier) {
    val roomList by vm.roomItems.observeAsState()
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()
    val context = LocalContext.current

    // Get sessions on first render
    if(roomList?.isEmpty() == true && !loading){
        vm.getRooms(context)
    }

    val scrollState = rememberLazyListState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(loading),
        onRefresh = { vm.getRooms(context) },
    ) {
        Column {
            if (error.isNotBlank()) {
                NetworkError(text = error)
            }

            LazyColumn(state = scrollState, modifier = modifier) {
                items(roomList!!) { item: Room ->
                    RoomRow( item, modifier = modifier )
                }
            }
        }
    }
}
