package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardScreenViewModel(
    private val navigationManager: NavigationManager
) : ViewModel() {
    private var _currentSession = MutableLiveData<Session>()
    val currentSessionItems: LiveData<Session> = _currentSession;
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading
    private var _session = MutableLiveData(listOf<Session>())
    val sessionItems: LiveData<List<Session>> = _session

    fun navigateToSessionList() {
        navigationManager.navigate(Screen.SessionList.navigationCommand())
    }

    fun getCurrentSession(context: Context) {
        _loading.value = true
        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.getCurrentSession("Bearer " + token.toString())
            .enqueue(object : Callback<Session> {
                override fun onResponse(call: Call<Session>, response: Response<Session>) {
                    _loading.value = false
                    response.body()?.let { it ->
                        _currentSession.value = it
                    }
                }

                override fun onFailure(call: Call<Session>, t: Throwable) {
                    _loading.value = false
                    t.printStackTrace()
                    println("request wrong")
                }
            })
    }

    fun getSessions(context: Context) {
        _loading.value = true
        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.getDashboardSessions("Bearer " + token.toString())
            .enqueue(object : Callback<List<Session>> {
                override fun onResponse(
                    call: Call<List<Session>>,
                    response: Response<List<Session>>
                ) {
                    _loading.value = false
                    response.body()?.let { it ->
                        _session.value = it.toMutableList()
                    }
                }

                override fun onFailure(call: Call<List<Session>>, t: Throwable) {
                    _loading.value = false
                    t.printStackTrace()
                    println("request wrong")
                }
            })
    }
}
