package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.GetSessionsAsync
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SessionListScreenViewModel(
    private val getSessionsAsyncUseCase: GetSessionsAsync,
    private val navigationManager: NavigationManager
    ) : ViewModel() {

    // See https://code.luasoftware.com/tutorials/android/jetpack-compose-load-data/
    var sessionsAsync by mutableStateOf(AsyncOperation.undefined())

    init {
        this.update()
    }

    fun getSessionsAsync(): Flow<AsyncOperation> {
        return getSessionsAsyncUseCase()
    }

    fun update() {
        viewModelScope.launch {
            getSessionsAsync().collect {
                sessionsAsync = it
            }
        }
    }

    fun navigateToSession(sessionId: String ) {
        // TODO: Create session page
        // navigationManager.navigate( Screen.SessionDetail.navigationCommand( sessionId ) )
    }
}
