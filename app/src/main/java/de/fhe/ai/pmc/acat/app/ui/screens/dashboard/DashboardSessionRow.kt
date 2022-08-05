package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fhe.ai.pmc.acat.domain.Session
import java.time.format.DateTimeFormatter

@Composable
fun DashboardSessionRow(modifier: Modifier, text: String, session : Session) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text, color = Color.DarkGray,
            fontWeight = FontWeight.Bold, fontSize = 14.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(session.room?.name ?: "", fontWeight = FontWeight.Bold)
        }
    }
    Spacer(modifier = Modifier.height(3.dp))
    Row {
        val startFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy   HH:mm")
        val endFormatter = DateTimeFormatter.ofPattern("HH:mm")
        Spacer(modifier = modifier.width(10.dp))
        Row {
            Text(text = session.startTime.format(startFormatter),fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = " - ")
            Text(text = session.endTime?.format(endFormatter) ?: "Running", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}
