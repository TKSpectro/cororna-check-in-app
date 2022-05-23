package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import de.fhe.ai.pmc.acat.app.ui.screens.userlist.UserListScreenAppBarActions

val RootScreens = listOf(
    Screen.UserList,
    Screen.Map,
    Screen.Settings
)

sealed class Screen(
    val title: String = "Title",
    val icon: ImageVector = Icons.Filled.Favorite,
    var appBarActions: @Composable RowScope.() -> Unit = {},
    val route: String = ""
    ) {

    open fun navigationCommand( vararg value: Any ) = object : NavigationCommand {
            override val arguments = emptyList<NamedNavArgument>()
            override val destination = route
    }

    object UserList : Screen(
        title = "Users",
        icon = Icons.Filled.Home,
        appBarActions = UserListScreenAppBarActions,
        route = "UserList"
    )

    object UserDetail : Screen(
        title = "User",
        icon = Icons.Filled.AccountBox,
        route = "UserDetail/{userId}"
    ) {
        override fun navigationCommand(vararg value : Any ) = object : NavigationCommand {

            override val arguments = listOf(
                navArgument("userId") {
                    type = NavType.LongType
                }
            )
            override val destination = "UserDetail/${value[0]}"
        }
    }

    object Map : Screen(
        title ="Map",
        icon = Icons.Filled.Place,
        route = "Map"
    )

    object Settings : Screen(
        title = "Settings",
        icon = Icons.Filled.Settings,
        route = "Settings"
    )

}

