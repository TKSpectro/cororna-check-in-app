package de.fhe.ai.pmc.acat.app.ui.screens.userlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.GetUsers
import de.fhe.ai.pmc.acat.domain.GetUsersAsync
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserListScreenViewModel(
    private val getUsersUseCase: GetUsers,
    private val getUsersAsyncUseCase: GetUsersAsync,
    private val navigationManager: NavigationManager
    ) : ViewModel() {

    // See https://code.luasoftware.com/tutorials/android/jetpack-compose-load-data/
    var usersAsync by mutableStateOf(AsyncOperation.undefined())

    init {
        this.update()
    }

//    suspend fun getUsers(): List<User> {
//        return getUsersUseCase().first()
//    }

    fun getUsersAsync(): Flow<AsyncOperation> {
        return getUsersAsyncUseCase()
    }

    fun update() {
        viewModelScope.launch {
            getUsersAsync().collect {
                usersAsync = it
            }
        }
    }

    fun navigateToAddUser() {
        navigationManager.navigate( Screen.UserDetail.navigationCommand(1) )
    }

    fun navigateToUser( userId: String ) {
        navigationManager.navigate( Screen.UserDetail.navigationCommand( userId ) )
    }

}
