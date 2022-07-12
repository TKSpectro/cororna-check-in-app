package de.fhe.ai.pmc.acat.app.ui.screens.roomdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import de.fhe.ai.pmc.acat.app.ui.components.CustomCard
import de.fhe.ai.pmc.acat.app.ui.components.NetworkError
import de.fhe.ai.pmc.acat.app.ui.screens.sessionlist.SessionRow
import de.fhe.ai.pmc.acat.domain.Room

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