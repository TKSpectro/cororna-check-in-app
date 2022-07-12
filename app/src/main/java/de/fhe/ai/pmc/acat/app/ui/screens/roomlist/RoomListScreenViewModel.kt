package de.fhe.ai.pmc.acat.app.ui.screens.roomlist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.domain.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomListScreenViewModel (
    private val navigationManager: NavigationManager
) : ViewModel() {
    private var _roomItems = MutableLiveData(listOf<Room>())
    val roomItems: LiveData<List<Room>> = _roomItems

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun getRooms(context: Context){
        _error.value = ""
        _loading.value = true

        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.listRooms("Bearer " + token.toString()).enqueue(object:
            Callback<List<Room>> {
            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                _loading.value = false
                response.body()?.let { it ->
                    _roomItems.value = it.toMutableList()
                }
            }

            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
                _loading.value = false
                t.printStackTrace()
                _error.value = "Some error occurred while fetching data"
            }
        })
    }
}