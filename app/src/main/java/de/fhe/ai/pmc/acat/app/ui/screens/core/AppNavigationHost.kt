package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.pmc.acat.app.ui.screens.dashboard.DashboardScreen
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreen
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreen
import de.fhe.ai.pmc.acat.app.ui.screens.map.MapScreen
import de.fhe.ai.pmc.acat.app.ui.screens.scan.ScanScreen
import de.fhe.ai.pmc.acat.app.ui.screens.settings.SettingsScreen
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreenViewModel
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

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
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {
        composable(Screen.UserList.route) {
            val vm by viewModel<UserListScreenViewModel>()

            Screen.UserList.prepareAppBarActions( vm )
            onNavigation( Screen.UserList )

            UserListScreen( vm )
        }
        composable(
            Screen.UserDetail.route,
            Screen.UserDetail.navigationCommand(0).arguments
        ) { entry ->
            val userId = entry.arguments?.getLong("userId")
            val vm by viewModel<DetailScreenViewModel>( parameters = { parametersOf( userId ) })

            Screen.UserDetail.prepareAppBarActions( LocalContext.current, vm )
            onNavigation( Screen.UserDetail )

            DetailScreen( vm )
        }
        composable(Screen.Dashboard.route) {
            onNavigation( Screen.Dashboard )
            DashboardScreen()
        }
        composable(Screen.Scan.route) {
            onNavigation( Screen.Scan )
            ScanScreen()
        }
        composable(Screen.Settings.route) {
            onNavigation( Screen.Settings )
            SettingsScreen()
        }
    }
}
