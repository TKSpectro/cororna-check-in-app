package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.app.network.SessionType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun SessionsListScreen(vm: SessionListScreenViewModel, modifier: Modifier = Modifier) {
    val sessionList by vm.sessionItems.observeAsState()
    vm.getSessions()

    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    LazyColumn{
        itemsIndexed(items = sessionList!!) { index, item: SessionType ->
            Column(Modifier.padding(6.dp)) {
                Text(text = item.Id)
                Row {
                    Text(text = "From: ")
                    Text(text = LocalDateTime.parse(item.StartTime).format(pattern))
                }
                Row {
                    Text(text = "To: ")
                    Text(text = LocalDateTime.parse(item.EndTime).format(pattern))
                }
            }
        }
    }
}
