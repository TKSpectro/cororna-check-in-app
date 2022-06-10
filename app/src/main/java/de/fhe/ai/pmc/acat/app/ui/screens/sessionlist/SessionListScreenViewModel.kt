package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.network.SessionType
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.domain.GetSessionsAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionListScreenViewModel(
    private val getSessionsAsyncUseCase: GetSessionsAsync,
    private val navigationManager: NavigationManager
    ) : ViewModel() {
    private var _sessionItems = MutableLiveData(listOf<SessionType>())
    val sessionItems: LiveData<List<SessionType>> = _sessionItems

    fun getSessions(){
        Network.service.listSessions().enqueue(object: Callback<List<SessionType>> {
            override fun onResponse(call: Call<List<SessionType>>, response: Response<List<SessionType>>) {
                response.body()?.let { it ->
                    _sessionItems.value = it.toMutableList()
                }
            }

            override fun onFailure(call: Call<List<SessionType>>, t: Throwable) {
                t.printStackTrace()
                println("request wrong")
            }
        })
    }

    fun navigateToSession(sessionId: String ) {
        // TODO: Create session page
        // navigationManager.navigate( Screen.SessionDetail.navigationCommand( sessionId ) )
    }
}
