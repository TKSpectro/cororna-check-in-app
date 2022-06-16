package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import de.fhe.ai.pmc.acat.app.ui.components.CustomCard
import de.fhe.ai.pmc.acat.domain.Room

@Composable
fun DashboardScreen(vm: DashboardScreenViewModel) {
    val estateList by vm.roomItems.observeAsState()
    val context = LocalContext.current

    vm.getRooms(context)

    Column {
        CustomCard(heading = "Test Heading") {
            Text("This is some other information text")
        }
        CustomCard(heading = "Your current status") {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Your current status is:")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Check, contentDescription = null, tint = Color.Green)
                    Text("Not infected")
                }
            }
        }
        CustomCard(heading = "Last sessions", modifier = Modifier.clickable { vm.navigateToSessionList() }) {
            Text("Session 1")
            Text("Session 2")
            Text("Session 3")
        }
        CustomCard(heading = "Rooms") {
            LazyColumn{
                itemsIndexed(items = estateList!!) { _, item: Room ->
                    Text(text = item.name)
                }
            }
        }
    }
}
