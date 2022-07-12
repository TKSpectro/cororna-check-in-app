package de.fhe.ai.pmc.acat.app.ui.screens.roomdetails

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import de.fhe.ai.pmc.acat.app.ui.components.CustomCard

@Composable
fun RoomDetailsScreen(vm: RoomDetailsScreenViewModel) {
    val room by vm.roomItems.observeAsState()
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()
    val context = LocalContext.current

    // Get sessions on first render
    if(!loading){
        vm.getRoom(context)
    }

    val scrollState = rememberLazyListState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(loading),
        onRefresh = { vm.getRoom(context) },
    ) {
        CustomCard(heading = "Room Details") {
            LazyColumn {
                item {
                    Text(text = "Room name: " + room?.name ?: "")
                    Text(text = "Max. Participants: " + room?.maxParticipants ?: "")
                    Text(text = "Max. Duration: " + room?.maxDuration ?: "")
                }
            }
        }
    }
}