package de.fhe.ai.pmc.acat.app.ui.screens.roomlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.domain.Room

@Composable
fun RoomRow(
    room: Room,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(6.dp)
            .shadow(elevation = 6.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.surface)
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row() {
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    room.name ?: "",
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth(0.9f))

            }
            Row {

                Spacer(modifier = modifier.width(20.dp))
                Row {
                    Text("Room name: " + room.name ?: "")
                }
            }
            Row {

                Spacer(modifier = modifier.width(20.dp))
                Row {
                    Text("Max. Participants: " + room?.maxParticipants ?: "")
                }
            }
            Row {

                Spacer(modifier = modifier.width(20.dp))
                Row {
                    Text("Max. Duration: " + room?.maxDuration + " minutes")
                }
            }
        }
    }
}
