package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreen
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreen
import de.fhe.ai.pmc.acat.app.ui.screens.map.MapScreen
import de.fhe.ai.pmc.acat.app.ui.screens.settings.SettingsScreen
import org.koin.androidx.compose.inject

@Composable
fun AppNavigationHost(
    navCtrl: NavHostController,
    onNavigation: ( screen: Screen ) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationManager by inject<NavigationManager>()

    navigationManager.commands.collectAsState().value.also { command ->
        if (command.destination.isNotEmpty())
            navCtrl.navigate(command.destination)
    }

    NavHost(
        navController = navCtrl,
        startDestination = Screen.UserList.route,
        modifier = modifier
    ) {
        composable(Screen.UserList.route) {
            onNavigation( Screen.UserList )
            UserListScreen()
        }
        composable(
            Screen.UserDetail.route,
            Screen.UserDetail.navigationCommand(0).arguments
        ) { entry ->
            val userId = entry.arguments?.getLong("userId")
            onNavigation( Screen.UserDetail )
            DetailScreen( userId )
        }
        composable(Screen.Map.route) {
            onNavigation( Screen.Map )
            MapScreen()
        }
        composable(Screen.Settings.route) {
            onNavigation( Screen.Settings )
            SettingsScreen()
        }
    }
}
