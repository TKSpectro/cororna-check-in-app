package de.fhe.ai.pmc.acat.app.ui.screens.core

import android.content.Context
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import de.fhe.ai.pmc.acat.app.ui.screens.detail.DetailScreenViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreenViewModel

val RootScreens = listOf(
    Screen.Dashboard,
    Screen.Scan,
    Screen.Settings,
)

sealed class Screen(
    val title: String = "Title",
    val icon: ImageVector = Icons.Filled.Favorite,
    val route: String = ""
    ) {

    var appBarActions: @Composable RowScope.() -> Unit = {}
        protected set

    open fun prepareAppBarActions(vararg values: Any) {}

    open fun navigationCommand( vararg value: Any ) = object : NavigationCommand {
            override val arguments = emptyList<NamedNavArgument>()
            override val destination = route
    }

    object Undefined : Screen(
        title = "Undefined",
        icon = Icons.Filled.Warning,
        route = ""
    )

    object UserList : Screen(
        title = "Users",
        icon = Icons.Filled.Home,
        route = "UserList"
    ) {
        override fun prepareAppBarActions(vararg values: Any) {
            if(values[0] !is UserListScreenViewModel) error( "First Parameter must be of type *UserListScreenViewModel*" )
            val viewModel = values[0] as UserListScreenViewModel

            appBarActions = {
                IconButton(
                    onClick = { viewModel.navigateToAddUser() }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
            }
        }
    }

    object UserDetail : Screen(
        title = "User",
        icon = Icons.Filled.AccountBox,
        route = "UserDetail/{userId}"
    ) {
        override fun prepareAppBarActions(vararg values: Any) {
            if(values[0] !is Context) error( "First Parameter must be of type *Context*")
            if(values[1] !is DetailScreenViewModel) error( "Second Parameter must be of type *DetailScreenViewModel" )

            val context = values[0] as Context
            val viewModel = values[1] as DetailScreenViewModel

            appBarActions = {
                IconButton( onClick = { viewModel.share(context) }
                ) {
                    Icon(Icons.Filled.Share, contentDescription = null)
                }
            }
        }

        override fun navigationCommand(vararg value : Any ) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("userId") {
                    type = NavType.LongType
                }
            )
            override val destination = "UserDetail/${value[0]}"
        }
    }

    object Dashboard : Screen(
        title = "Dashboard",
        icon = Icons.Filled.Home,
        route = "Dashboard"
    )

    object Scan : Screen(
        title = "Scan",
        icon = Icons.Filled.Search,
        route = "Scan"
    )

    object Settings : Screen(
        title = "Settings",
        icon = Icons.Filled.Settings,
        route = "Settings"
    )

    object Login : Screen(
        title = "Login",
        icon = Icons.Filled.Lock,
        route = "Login"
    )

    object Register : Screen(
        title = "Register",
        icon = Icons.Filled.Lock,
        route = "Register"
    )

    object SessionList : Screen(
        title = "Sessions",
        icon = Icons.Filled.DateRange,
        route = "SessionList"
    )

    object RoomDetails : Screen(
        title = "Room Details",
        icon = Icons.Filled.Place,
        route = "RoomDetails/{roomId}"
    ) {
        override fun navigationCommand(vararg value: Any) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("roomId") {
                    type = NavType.StringType
                }
            )
            override val destination = "RoomDetails/${value[0]}"
        }
    }

    object RoomList : Screen(
        title = "Rooms",
        icon = Icons.Filled.Place,
        route = "RoomList"
    )
}
