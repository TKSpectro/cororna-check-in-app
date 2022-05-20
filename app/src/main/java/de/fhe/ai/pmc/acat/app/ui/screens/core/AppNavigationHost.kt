package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.pmc.acat.app.ui.screens.main.MainScreen
import de.fhe.ai.pmc.acat.app.ui.screens.map.MapScreen
import de.fhe.ai.pmc.acat.app.ui.screens.settings.SettingsScreen

@Composable
fun AppNavigationHost(
    onNavigation: ( screen:ScreensEnum ) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = LocalNavCtrl.current,
        startDestination = ScreensEnum.Main.name,
        modifier = modifier
    ) {
        composable(ScreensEnum.Main.name) {
            onNavigation( ScreensEnum.Main )
            MainScreen()
        }
        composable(ScreensEnum.Map.name) {
            onNavigation( ScreensEnum.Map )
            MapScreen()
        }
        composable(ScreensEnum.Settings.name) {
            onNavigation( ScreensEnum.Settings )
            SettingsScreen()
        }
    }
}
