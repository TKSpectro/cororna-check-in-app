package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardScreenViewModel(
    private val navigationManager: NavigationManager
) : ViewModel() {
    fun navigateToSessionList() {
        navigationManager.navigate(Screen.SessionList.navigationCommand())
    }

    private var _roomItems = MutableLiveData(listOf<Room>())
    val roomItems: LiveData<List<Room>> = _roomItems

    fun getRooms(context: Context){
        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.listRooms("Bearer " + token.toString()).enqueue(object: Callback<List<Room>>{
            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                response.body()?.let { it ->
                    _roomItems.value = it.toMutableList()
                }
            }

            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                t.printStackTrace()
                println("request wrong")
            }
        })
    }
}
