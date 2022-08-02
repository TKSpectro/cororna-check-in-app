package de.fhe.ai.pmc.acat.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.app.ui.theme.lightGreen

@Composable
fun CustomCard(modifier: Modifier = Modifier, boxModifier: Modifier = Modifier, heading: String? = null, color : Color = Color.Black, backgroundColor : Color = Color.White  ,content: @Composable () -> Unit){
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
            backgroundColor = (backgroundColor)
    ) {
        Box(modifier = boxModifier.padding(8.dp)){
            Column {
                if (heading != null && color != Color.Black) {
                    Heading(heading, color)
                }
                else if (heading != null )
                    Heading(heading)
                content()
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomCard(
) {
    CustomCard(){
        Text("Test")
    }
}

@Preview
@Composable
fun PreviewCustomCardWithHeading(
) {
    CustomCard(heading = "Heading", color = MaterialTheme.colors.lightGreen){
        Text("Test", color = MaterialTheme.colors.lightGreen)
    }
}

@Preview
@Composable
fun PreviewCustomCardWithHeadingAndColumn(
) {
    CustomCard(heading = "Heading"){
        Column {
            Text("First line of text")
            Text("Second line of text")
        }
    }
}