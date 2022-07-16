package de.fhe.ai.pmc.acat.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.fhe.ai.pmc.acat.app.ui.theme.divider

@Composable
fun CurrentSessionCard(
    modifier: Modifier = Modifier, boxModifier: Modifier = Modifier,
    heading: String? = null, color: Color = Color.Black, content: @Composable () -> Unit
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(modifier = boxModifier.padding(12.dp).fillMaxWidth()) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                if (heading != null && color != Color.Black) {
                    Heading(heading, color, size = 18)
                } else if (heading != null) {
                    Heading(heading, size = 22)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            TabRowDefaults.Divider(color = MaterialTheme.colors.primary, thickness = 1.dp);

            Column(modifier = boxModifier.padding(12.dp)) {

                currentSessionRow("Room:", "5.1.05")

                divider();
                currentSessionRow("Joined at:", "12:05")

                divider();
                currentSessionRow("Reaining time:", "12 Min")

                divider();
                currentSessionRow("Participants:", "12")
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}


@Composable
fun divider() {
    Spacer(modifier = Modifier.height(10.dp))
    TabRowDefaults.Divider(color = Color.Gray, thickness = 1.dp);
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun currentSessionRow(text: String, value: String) {
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
            Text(value, fontWeight = FontWeight.Bold)
        }
    }
}


@Preview
@Composable
fun PreviewCrrentSessionCard(
) {
    CurrentSessionCard(heading = "Current Session", color = MaterialTheme.colors.primary) {
        Text("Test")
    }
}