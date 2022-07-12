package de.fhe.ai.pmc.acat.app.ui.screens.core

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

val LocalScaffoldState =
    staticCompositionLocalOf<ScaffoldState> { error("no scaffolded state set") }

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Undefined) }

    CompositionLocalProvider(LocalScaffoldState provides scaffoldState) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(currentScreen) },
            bottomBar = {
                if ((currentRoute(navController) != "Register") &&
                    (currentRoute(navController) != "Login")
                ) {
                    BottomBar(navController)
                }
            }
        ) { innerPadding ->
            AppNavigationHost(
                navController,
                onNavigation = {
                    currentScreen = it
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}