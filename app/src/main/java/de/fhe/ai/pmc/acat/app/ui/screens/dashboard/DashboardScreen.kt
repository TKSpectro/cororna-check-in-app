package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import de.fhe.ai.pmc.acat.app.ui.components.CustomCard
import de.fhe.ai.pmc.acat.app.ui.screens.util.PREVIEW_BACKGROUND_COLOR

@Composable
fun DashboardScreen() {
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
        CustomCard(heading = "Last sessions") {
            Text("Session 1")
            Text("Session 2")
            Text("Session 3")
        }
        CustomCard {
            Text("Some random information")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = PREVIEW_BACKGROUND_COLOR
)
@Composable
fun PreviewDashboardScreen(
) {
    DashboardScreen()
}