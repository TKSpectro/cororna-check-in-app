package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar() {
    BottomNavigation {
        val navController = LocalNavCtrl.current

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        for( item in Screen.values() )
        {
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = null) },
                label = { Text(item.name) },
                selected = currentRoute == item.name,
                onClick = {
                    navController.navigate(item.name) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}