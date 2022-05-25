package de.fhe.ai.pmc.acat.app.ui.screens.userlist

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import de.fhe.ai.pmc.acat.app.ui.screens.util.AsyncPlaceholderView
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.AsyncOperationState
import de.fhe.ai.pmc.acat.domain.User

@Composable
fun UserListScreen(vm: UserListScreenViewModel, modifier: Modifier = Modifier) {
    val usersAsync = vm.usersAsync

    Column(modifier = modifier) {

        val data: List<User>
        if( usersAsync.payload is List<*> ) {
            data = usersAsync.payload as List<User>

            UserList(
                data,
                usersAsync.status != AsyncOperationState.SUCCESS,
                onRefresh = { vm.update() },
                modifier = modifier
            ) {
                vm.navigateToUser(it)
            }
        }
        else {
            data = emptyList<User>()
            AsyncPlaceholderView(asyncOperation = AsyncOperation.loading())
        }
            

        
    }
}
