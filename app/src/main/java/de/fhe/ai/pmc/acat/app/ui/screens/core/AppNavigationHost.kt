package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreen
import de.fhe.ai.pmc.acat.app.ui.screens.main.MainScreen
import de.fhe.ai.pmc.acat.app.ui.screens.map.MapScreen
import de.fhe.ai.pmc.acat.app.ui.screens.settings.SettingsScreen

@Composable
fun AppNavigationHost(
    onNavigation: ( screen:ScreensEnum ) -> Unit,
    modifier: Modifier = Modifier
) {

    val navCtrl = LocalNavCtrl.current

    NavHost(
        navController = navCtrl,
        startDestination = ScreensEnum.Main.name,
        modifier = modifier
    ) {
        composable(ScreensEnum.Main.name) {
            onNavigation( ScreensEnum.Main )
            MainScreen()
        }
        composable(
            "${ScreensEnum.UserDetail.name}/{userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            val userId = entry.arguments?.getLong("userId")
            onNavigation( ScreensEnum.UserDetail )
            DetailScreen( userId )
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
