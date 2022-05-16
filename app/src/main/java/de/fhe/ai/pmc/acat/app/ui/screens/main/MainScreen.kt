package de.fhe.ai.pmc.acat.app.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val vm = getViewModel<MainScreenViewModel>()
    val users by remember(vm) { vm.getUsers() }.collectAsState(listOf())

    Column(modifier = modifier.background( MaterialTheme.colors.primaryVariant )) {
        UserList(users, modifier)
    }
}
