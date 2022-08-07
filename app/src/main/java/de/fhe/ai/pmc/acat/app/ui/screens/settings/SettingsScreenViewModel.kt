package de.fhe.ai.pmc.acat.app.ui.screens.settings

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.AuthResponse
import de.fhe.ai.pmc.acat.domain.InfectedResponse
import de.fhe.ai.pmc.acat.domain.RemoveProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsScreenViewModel(
    private val navigationManager: NavigationManager
) : ViewModel() {
    fun logout(context: Context) {
        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.remove("auth_token")
        editor.apply()
        navigationManager.navigate(Screen.Login.navigationCommand())
    }

    fun removeProfile(context: Context) {
        val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
        val token = sharedPref.getString("auth_token", null)

        Network.service.removeProfile("Bearer " + token.toString())
            .enqueue(object : Callback<RemoveProfileResponse> {
                override fun onResponse(
                    call: Call<RemoveProfileResponse>,
                    response: Response<RemoveProfileResponse>
                ) {
                    response.body()?.let { it ->
                        Toast.makeText(
                            context,
                            it.message + "",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    val editor = sharedPref.edit()
                    editor.remove("auth_token")
                    editor.apply()
                    navigationManager.navigate(Screen.Login.navigationCommand())
                }

                override fun onFailure(call: Call<RemoveProfileResponse>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Profile couldn't be deleted",
                        Toast.LENGTH_SHORT
                    ).show()

                    t.printStackTrace()
                }
            })
    }
}