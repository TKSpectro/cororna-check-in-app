package de.fhe.ai.pmc.acat.app.ui.screens.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen( vm: DetailScreenViewModel ) {
    Text("Detail: ${vm.userId}")
}
