package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppBar( screen: ScreensEnum ) {
    TopAppBar(
        title = {
                Text( text = screen.name )
        },
        navigationIcon = {
            Icon(
                screen.icon,
                screen.name,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    )
}

@Preview()
@Composable
fun HomeAppBar() {
    AppBar(screen = ScreensEnum.Main)
}

@Preview()
@Composable
fun MapAppBar() {
    AppBar(screen = ScreensEnum.Map)
}

@Preview()
@Composable
fun SettingsAppBar() {
    AppBar(screen = ScreensEnum.Settings)
}
