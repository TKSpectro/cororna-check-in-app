package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigationHost(
    onNavigation: ( screen:Screen ) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = LocalNavCtrl.current,
        startDestination = Screen.Main.name,
        modifier = modifier
    ) {
        composable(Screen.Main.name) {
            onNavigation( Screen.Main )
            Text("Hello")
        }
        composable(Screen.Map.name) {
            onNavigation( Screen.Map )
            Text("Map")
        }
        composable(Screen.Settings.name) {
            onNavigation( Screen.Settings )
            Text("Settings")
        }
    }
}