package de.fhe.ai.pmc.acat.app.ui.screens.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import de.fhe.ai.pmc.acat.app.ui.screens.util.AsyncPlaceholderView
import de.fhe.ai.pmc.acat.domain.AsyncOperationState
import de.fhe.ai.pmc.acat.domain.User

@Composable
fun UserListScreen(vm: UserListScreenViewModel, modifier: Modifier = Modifier) {
    val usersAsync = vm.usersAsync

    Column(modifier = modifier) {
        if (usersAsync.status == AsyncOperationState.SUCCESS) {
            UserList( usersAsync.payload as List<User>, modifier ) {
                vm.navigateToUser(it)
            }
        }
        else {
            AsyncPlaceholderView( usersAsync )
        }
    }
}
