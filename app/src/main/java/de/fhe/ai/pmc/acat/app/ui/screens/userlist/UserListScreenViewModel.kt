package de.fhe.ai.pmc.acat.app.ui.screens.userlist

import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.GetUsers
import de.fhe.ai.pmc.acat.domain.GetUsersAsync
import de.fhe.ai.pmc.acat.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class UserListScreenViewModel(
    private val getUsersUseCase: GetUsers,
    private val getUsersAsyncUseCase: GetUsersAsync,
    private val navigationManager: NavigationManager
    ) : ViewModel() {

    suspend fun getUsers(): List<User> {
        return getUsersUseCase().first()
    }

    fun getUsersAsync(): Flow<AsyncOperation> {
        return getUsersAsyncUseCase()
    }

    fun navigateToAddUser() {
        navigationManager.navigate( Screen.UserDetail.navigationCommand(1) )
    }

    fun navigateToUser( userId: Long ) {
        navigationManager.navigate( Screen.UserDetail.navigationCommand( userId ) )
    }

}
