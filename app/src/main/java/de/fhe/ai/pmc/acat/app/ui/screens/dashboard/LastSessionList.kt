package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.app.ui.components.Heading

@Composable
fun LastSessionListCard(
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
                    Heading(heading, color, size = 16)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TabRowDefaults.Divider(color = MaterialTheme.colors.primary, thickness = 1.dp);

            Column(modifier = boxModifier.padding(8.dp)) {
                content()
            }
        }
    }
}