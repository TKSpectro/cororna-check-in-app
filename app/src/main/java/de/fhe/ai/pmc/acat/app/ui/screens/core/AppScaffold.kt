package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val LocalNavCtrl = staticCompositionLocalOf<NavHostController> { error("no nav controller set") }
val LocalScaffoldState = staticCompositionLocalOf<ScaffoldState> { error("no scaffolded state set") }

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var currentScreen by rememberSaveable { mutableStateOf( ScreensEnum.Main ) }

    CompositionLocalProvider(
        LocalNavCtrl provides navController,
        LocalScaffoldState provides scaffoldState) {

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar(currentScreen) },
            bottomBar = { BottomBar() }
        ) { AppNavigationHost(
                onNavigation = {
                    currentScreen = it
                }
            )
        }

    }
}
