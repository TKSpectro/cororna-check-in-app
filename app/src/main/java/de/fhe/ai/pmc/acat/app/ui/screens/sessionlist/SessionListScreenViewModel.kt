package de.fhe.ai.pmc.acat.app.ui.screens.sessionlist

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.GetSessionsAsync
import de.fhe.ai.pmc.acat.domain.InfectedResponse
import de.fhe.ai.pmc.acat.domain.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionListScreenViewModel(
    private val getSessionsAsyncUseCase: GetSessionsAsync,
    private val navigationManager: NavigationManager
    ) : ViewModel() {
    private var _sessionItems = MutableLiveData(listOf<Session>())
    val sessionItems: LiveData<List<Session>> = _sessionItems

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun getSessions(context: Context){
        _error.value = ""
        _loading.value = true

        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.listSessions("Bearer " + token.toString()).enqueue(object: Callback<List<Session>> {
            override fun onResponse(call: Call<List<Session>>, response: Response<List<Session>>) {
                _loading.value = false
                response.body()?.let { it ->
                    _sessionItems.value = it.toMutableList()
                }
            }

            override fun onFailure(call: Call<List<Session>>, t: Throwable) {
                _loading.value = false
                t.printStackTrace()
                _error.value = "Some error occurred while fetching data"
            }
        })
    }

    fun markAsInfected(context: Context, sessionId: String) {
        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.setOneSessionAsInfected("Bearer " + token.toString(), sessionId)
            .enqueue(object : Callback<InfectedResponse> {
                override fun onResponse(
                    call: Call<InfectedResponse>,
                    response: Response<InfectedResponse>
                ) {
                    response.body()?.let { it ->
                        Toast.makeText(
                            context,
                            it.message + "",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    //navigationManager.navigate(Screen.SessionList.navigationCommand())
                    getSessions(context)
                }

                override fun onFailure(call: Call<InfectedResponse>, t: Throwable) {
                    t.printStackTrace()
                    println("request wrong")
                }
            })
    }
}
