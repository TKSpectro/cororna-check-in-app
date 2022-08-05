package de.fhe.ai.pmc.acat.app.ui.screens.core

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.auth.LoginScreen
import de.fhe.ai.pmc.acat.app.ui.screens.auth.LoginScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.dashboard.DashboardScreen
import de.fhe.ai.pmc.acat.app.ui.screens.dashboard.DashboardScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreen
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.register.RegisterScreen
import de.fhe.ai.pmc.acat.app.ui.screens.register.RegisterScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.roomdetails.RoomDetailsScreen
import de.fhe.ai.pmc.acat.app.ui.screens.roomdetails.RoomDetailsScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.roomlist.RoomListScreen
import de.fhe.ai.pmc.acat.app.ui.screens.roomlist.RoomListScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.scan.ScanScreen
import de.fhe.ai.pmc.acat.app.ui.screens.scan.ScanScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.sessionlist.SessionListScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.sessionlist.SessionsListScreen
import de.fhe.ai.pmc.acat.app.ui.screens.settings.SettingsScreen
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreen
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreenViewModel
import de.fhe.ai.pmc.acat.domain.MeResponse
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    // If the user is already logged in we redirect to dashboard
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
    val authToken = sharedPref.getString("auth_token", null)

    NavHost(
        navController = navCtrl,
        startDestination = if (authToken != null) Screen.Dashboard.route else Screen.Login.route,
        modifier = modifier
    ) {
        suspend fun requestTokenValid() = coroutineScope {
            launch {
                Network.service.me("Bearer " + authToken).enqueue(object: Callback<MeResponse> {
                    override fun onResponse(call: Call<MeResponse>, response: Response<MeResponse>) {
                        response.raw().let { it ->
                            if (it.code() == 401){
                                Toast.makeText(
                                    context,
                                    "Please login again. Your last session expired",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val editor = sharedPref.edit()
                                editor.remove("auth_token")
                                editor.apply()

                                navigationManager.navigate(Screen.Login.navigationCommand())
                            }
                        }
                    }

                    override fun onFailure(call: Call<MeResponse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            }
        }

        fun checkTokenValid() = runBlocking {
            requestTokenValid()
        }

        if(authToken != null){
            checkTokenValid()
        }

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
        composable(Screen.RoomDetails.route, Screen.RoomDetails.navigationCommand(1).arguments) { entry ->
            val userId = entry.arguments?.getString("roomId")
            val vm by viewModel<RoomDetailsScreenViewModel>( parameters = { parametersOf( userId ) })

            onNavigation( Screen.RoomDetails )
            RoomDetailsScreen( vm )
        }
        composable(Screen.RoomList.route) {
            val vm by viewModel<RoomListScreenViewModel>()

            onNavigation( Screen.RoomList )
            RoomListScreen( vm )
        }
    }
}
