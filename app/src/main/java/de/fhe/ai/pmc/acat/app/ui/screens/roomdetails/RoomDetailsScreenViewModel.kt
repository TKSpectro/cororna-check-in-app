package de.fhe.ai.pmc.acat.app.ui.screens.roomdetails

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

class RoomDetailsScreenViewModel (
    private val roomId: String,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private var _roomDetails = MutableLiveData<Room>()
    val roomItems: LiveData<Room> = _roomDetails

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun getRoom(context: Context){
        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.getRoomById("Bearer " + token.toString(), roomId).enqueue(object:
            Callback<Room> {
            override fun onResponse(call: Call<Room>, response: Response<Room>) {
                response.body()?.let { it ->
                    _roomDetails.value = it
                }
            }

            override fun onFailure(call: Call<Room>, t: Throwable) {
                t.printStackTrace()
                println("request wrong")
            }
        })
    }
}
