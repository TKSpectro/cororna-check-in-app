package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import de.fhe.ai.pmc.acat.app.ui.components.CurrentSessionCard
import de.fhe.ai.pmc.acat.app.ui.components.CustomCard
import de.fhe.ai.pmc.acat.app.ui.screens.sessionlist.SessionRow

@Composable
fun DashboardScreen(vm: DashboardScreenViewModel) {
    val sessionList by vm.sessionItems.observeAsState()
    val currentSession by vm.currentSessionItems.observeAsState()
    val loading by vm.loading.collectAsState()
    val context = LocalContext.current

    vm.getSessions(context)

    // Get current session on first render
    if(currentSession == null && !loading){
        vm.getCurrentSession(context)
    }

    // Get sessions on first render
    if(sessionList == null && !loading){
        vm.getSessions(context)
    }

    Column {
        currentSession?.let { CurrentSessionCard(heading = "Current Session", color =  MaterialTheme.colors.primary, currentSession = it) }

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
        Column {
            sessionList?.forEach { session ->
                SessionRow( session, modifier = Modifier, onItemPressed = { vm.navigateToSession(session.id) } )
            }
        }
    }
}

