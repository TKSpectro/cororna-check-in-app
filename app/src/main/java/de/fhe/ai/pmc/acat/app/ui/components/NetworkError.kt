package de.fhe.ai.pmc.acat.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NetworkError(modifier: Modifier = Modifier, text: String, description: String? = null){
    CustomCard {
        // TODO: Style this better, especially when description is given
        Box(modifier = Modifier.padding(8.dp)){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Warning, contentDescription = null, tint = Color.Red, )
                Column {
                    Text(text)
                    if (description != null) {
                        Text(text = description, modifier = Modifier.padding(top = 2.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewNetworkError(
) {
    NetworkError(text = "This is an error")
}

@Preview
@Composable
fun PreviewNetworkError2(
) {
    NetworkError(text = "This is an error", description = "This is a longer description of the problem.")
}
