package de.fhe.ai.pmc.acat.app.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Heading(text: String){
    Text(text, fontWeight = FontWeight.Bold, fontSize = 16.sp)
}
