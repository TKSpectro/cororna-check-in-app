package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.fhe.ai.pmc.acat.app.ui.screens.util.AsyncPlaceholderView
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.AsyncOperationState
import de.fhe.ai.pmc.acat.domain.Session

@Composable
fun SessionsListScreen(vm: SessionListScreenViewModel, modifier: Modifier = Modifier) {
    val sessionsAsync = vm.sessionsAsync

    Column(modifier = modifier) {

        val data: List<Session>
        if( sessionsAsync.payload is List<*> ) {
            data = sessionsAsync.payload as List<Session>

            SessionList(
                data,
                sessionsAsync.status != AsyncOperationState.SUCCESS,
                onRefresh = { vm.update() },
                modifier = modifier
            ) {
                vm.navigateToSession(it)
            }
        }
        else {
            data = emptyList<Session>()
            AsyncPlaceholderView(asyncOperation = AsyncOperation.loading())
        }
    }
}
