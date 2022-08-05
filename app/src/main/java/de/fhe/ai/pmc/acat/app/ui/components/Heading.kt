package de.fhe.ai.pmc.acat.app.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Heading(text: String, color: Color? = Color.Black, size: Int = 16){
    if(color != null)
    Text(text, fontWeight = FontWeight.ExtraBold, fontSize = size.sp, color = color)
    else{
        Text(text, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

@Preview
@Composable
fun PreviewHeading(
) {
    Heading("test")
}
