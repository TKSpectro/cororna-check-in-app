package de.fhe.ai.pmc.acat.app.ui.screens.scan

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.Room
import de.fhe.ai.pmc.acat.domain.ScanBody
import de.fhe.ai.pmc.acat.domain.ScanResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class QRCode (
    val roomId: String,
    val qrCodeCreatedAt: String
)

class ScanScreenViewModel(private val navigationManager: NavigationManager) : ViewModel() {
    private var _sessionItems = MutableLiveData<Room>()
    val sessionItems: LiveData<Room> = _sessionItems

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    private val alreadyScanned = MutableStateFlow(false)

    fun showQRResult(context: Context, text: String) {
        // Parse the text from the QR-Code as json
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val q: JsonAdapter<QRCode> = moshi.adapter(QRCode::class.java)
        val qrCode = q.fromJson(text)

        Log.d("QRCodeAnalyzer", "QRCode scanned: $text")
        Log.d("QRCodeAnalyzer", "Parsed: ${qrCode?.roomId}")

        if (qrCode != null && !alreadyScanned.value) {
            startSession(qrCode, context)
            alreadyScanned.value = true
        }
    }

    fun showPermissionNotGivenError(context: Context) {
        Toast.makeText(
            context,
            "You need to give camera permissions to scan. Please go to the app settings and enable them.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun startSession(qrCode: QRCode, context: Context){
        _error.value = ""
        _loading.value = true

        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)
        
        val body = ScanBody(qrCode.roomId, qrCode.qrCodeCreatedAt)

        Network.service.scan("Bearer " + token.toString(), body).enqueue(object: Callback<ScanResponse> {
            override fun onResponse(call: Call<ScanResponse>, response: Response<ScanResponse>) {
                _loading.value = false
                response.body()?.let { it ->
                    Toast.makeText(
                        context,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    if(it.message == "Started session"){
                        val roomId = body.roomId
                        navigationManager.navigate(Screen.RoomDetails.navigationCommand( roomId ))
                    }else {
                        navigationManager.navigate(Screen.Dashboard.navigationCommand())
                    }
                }
            }

            override fun onFailure(call: Call<ScanResponse>, t: Throwable) {
                _loading.value = false
                t.printStackTrace()
                _error.value = "Some error occurred while fetching data"
            }
        })
    }
}
