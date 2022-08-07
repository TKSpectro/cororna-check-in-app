package de.fhe.ai.pmc.acat.app.ui.screens.settings

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import de.fhe.ai.pmc.acat.app.ui.theme.lightRed

@Composable
fun SettingsScreen(vm: SettingsScreenViewModel) {
    val context = LocalContext.current

    val modifier = Modifier
        .padding(vertical = 8.dp)
        .width(180.dp)
    val shape = RoundedCornerShape(30.dp)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        Button(
            modifier = modifier,
            shape = shape,
            onClick = {
            vm.logout(context)
        }) {
            Text("Logout")
        }

        Button(
            modifier = modifier,
            shape = shape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.lightRed,
                contentColor = Color.White
            ),
            onClick = {
            vm.removeProfile(context)
        }) {
            Text("Delete profile")
        }
    }

}
