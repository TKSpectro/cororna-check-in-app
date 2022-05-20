package de.fhe.ai.pmc.acat.app.ui.screens.main

import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.GetUsers
import de.fhe.ai.pmc.acat.domain.GetUsersAsync
import de.fhe.ai.pmc.acat.domain.User
import kotlinx.coroutines.flow.Flow

class MainScreenViewModel(
    private val getUsersUseCase: GetUsers,
    private val getUsersAsyncUseCase: GetUsersAsync,
    ) : ViewModel() {

    fun getUsers(): Flow<List<User>> {
        return getUsersUseCase()
    }

    fun getUsersAsync(): Flow<AsyncOperation> {
        return getUsersAsyncUseCase()
    }
}
