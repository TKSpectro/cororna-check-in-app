package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.app.ui.components.CurrentSessionCard
import de.fhe.ai.pmc.acat.app.ui.components.CustomCard
import de.fhe.ai.pmc.acat.app.ui.theme.greenBackground
import de.fhe.ai.pmc.acat.app.ui.theme.lightGreen
import de.fhe.ai.pmc.acat.app.ui.theme.lightRed
import de.fhe.ai.pmc.acat.app.ui.theme.redBackground

@Composable
fun DashboardScreen(vm: DashboardScreenViewModel) {
    val sessionList by vm.sessionItems.observeAsState()
    val currentSession by vm.currentSessionItems.observeAsState()
    val loading by vm.loading.collectAsState()
    val warning by vm.warning.collectAsState()
    val reported by vm.reported.collectAsState()
    val context = LocalContext.current
    val modifier = Modifier.padding(vertical = 1.dp).fillMaxWidth()
    val shape = RoundedCornerShape(30.dp)

    vm.getSessions(context)

    // Get current session on first render
    if (currentSession == null && !loading) {
        vm.getCurrentSession(context)
    }

    // Get sessions on first render
    if (sessionList == null && !loading) {
        vm.getSessions(context)
    }

    // check status on first render
    if (warning == "" && !loading) {
        vm.checkSessions(context)
    }

    // show or hide button
    if (reported == "" && !loading) {
        vm.checkInfection(context)
    }

    if (!loading) {
        Column {
            if (reported != "infected!") {
                CustomCard(
                    heading = "Do you want to report yourself as infected?",
                    color = Color.DarkGray
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Button(
                                modifier = modifier,
                                shape = shape,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.colors.lightRed,
                                    contentColor = Color.White
                                ),
                                onClick = {
                                    vm.setInfected(context)
                                }) {
                                Text("Report")
                            }
                        }
                    }
                }
            }

            currentSession?.let {
                CurrentSessionCard(
                    heading = "Current Session",
                    color = MaterialTheme.colors.primary,
                    currentSession = it
                )
            }

            if (warning != "Higher risk") {
                CustomCard(
                    color = MaterialTheme.colors.lightGreen,
                    backgroundColor = MaterialTheme.colors.greenBackground
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Your current status is:", color = Color.Black)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Check, contentDescription = null, tint = Color.Green)
                            Text(warning)
                        }
                    }
                }
            } else {
                CustomCard(
                    color = MaterialTheme.colors.lightRed,
                    backgroundColor = MaterialTheme.colors.redBackground
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Your current status is:", color = Color.White)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            //Icon(Icons.Filled.Check, contentDescription = null, tint = Color.Red)
                            Text(warning, color = Color.Red)
                        }
                    }
                }
            }

            LastSessionListCard(
                heading = "Last Session",
                color = Color.DarkGray,
                modifier = Modifier.clickable { vm.navigateToSessionList() }) {
                Column {
                    sessionList?.forEach { session ->
                        DashboardSessionRow(Modifier, "Room: ", session)
                        TabRowDefaults.Divider(
                            color = Color.Gray,
                            thickness = 1.dp
                        );
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}
