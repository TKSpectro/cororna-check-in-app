package de.fhe.ai.pmc.acat.app.ui.screens.settings

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(vm: SettingsScreenViewModel) {
    val context = LocalContext.current

    val modifier = Modifier.padding(vertical = 8.dp)
    val shape = RoundedCornerShape(30.dp)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        Button(modifier = modifier, shape = shape, onClick = {
            vm.logout(context)
        }) {
            Text("Logout")
        }

        Button(modifier = modifier, shape = shape, onClick = {
            vm.removeProfile(context)
        }) {
            Text("Delete profile")
        }
    }

}
