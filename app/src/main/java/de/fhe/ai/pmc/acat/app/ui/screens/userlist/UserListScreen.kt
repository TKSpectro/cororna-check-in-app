package de.fhe.ai.pmc.acat.app.ui.screens.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import de.fhe.ai.pmc.acat.app.ui.screens.core.LocalScaffoldState
import de.fhe.ai.pmc.acat.app.ui.screens.util.AsyncPlaceholderView
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.AsyncOperationState
import de.fhe.ai.pmc.acat.domain.User
import org.koin.androidx.compose.getViewModel

val UserListScreenAppBarActions: @Composable RowScope.() -> Unit = {
    val vm = getViewModel<UserListScreenViewModel>()

    IconButton(
        onClick = { vm.navigateToAddUser() }
    ) {
        Icon(Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
fun UserListScreen(modifier: Modifier = Modifier) {
    val vm = getViewModel<UserListScreenViewModel>()

    val usersAsync by remember(vm) { vm.getUsersAsync() }.collectAsState( AsyncOperation.undefined() )

    // Side Effect: Show Snack Bar on Status Changes
    val state = LocalScaffoldState.current
    LaunchedEffect( usersAsync ) {
        if( usersAsync.status != AsyncOperationState.UNDEFINED )
        {
            state.snackbarHostState.showSnackbar(
                message  = "${usersAsync.message}...",
                duration = SnackbarDuration.Short
            )
        }
    }

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
