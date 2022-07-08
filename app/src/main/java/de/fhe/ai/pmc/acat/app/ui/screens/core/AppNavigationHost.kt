package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.pmc.acat.app.ui.screens.auth.LoginScreen
import de.fhe.ai.pmc.acat.app.ui.screens.auth.LoginScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.dashboard.DashboardScreen
import de.fhe.ai.pmc.acat.app.ui.screens.dashboard.DashboardScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreen
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.register.RegisterScreen
import de.fhe.ai.pmc.acat.app.ui.screens.register.RegisterScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.scan.ScanScreen
import de.fhe.ai.pmc.acat.app.ui.screens.scan.ScanScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.sessionlist.SessionListScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.sessionlist.SessionsListScreen
import de.fhe.ai.pmc.acat.app.ui.screens.settings.SettingsScreen
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreen
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
        startDestination = Screen.Login.route,
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
            val vm by viewModel<DashboardScreenViewModel>()

            onNavigation( Screen.Dashboard )
            DashboardScreen( vm )
        }
        composable(Screen.Scan.route) {
            val vm by viewModel<ScanScreenViewModel>()

            onNavigation( Screen.Scan )
            ScanScreen( vm )
        }
        composable(Screen.Settings.route) {
            onNavigation( Screen.Settings )
            SettingsScreen()
        }
        composable(Screen.Login.route){
            val vm by viewModel<LoginScreenViewModel>()

            onNavigation( Screen.Login )
            LoginScreen( vm )
        }
        composable(Screen.Register.route){
            val vm by viewModel<RegisterScreenViewModel>()

            onNavigation( Screen.Register )
            RegisterScreen( vm )
        }
        composable(Screen.SessionList.route){
            val vm by viewModel<SessionListScreenViewModel>()

            onNavigation( Screen.SessionList )
            SessionsListScreen( vm )
        }
    }
}
