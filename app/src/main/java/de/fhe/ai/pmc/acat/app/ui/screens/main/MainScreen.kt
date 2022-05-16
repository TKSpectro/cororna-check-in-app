package de.fhe.ai.pmc.acat.app.ui.screens.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen() {
    Text("Hello")
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}