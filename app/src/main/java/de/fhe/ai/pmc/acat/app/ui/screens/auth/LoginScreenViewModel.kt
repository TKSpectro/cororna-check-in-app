package de.fhe.ai.pmc.acat.app.ui.screens.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreenViewModel(
    private val navigationManager: NavigationManager
) : ViewModel() {
    fun showInputErrorToast(context: Context){
        Toast.makeText(
            context,
            "Please enter email and password",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun showEmailErrorToast(context: Context){
        Toast.makeText(
            context,
            "The given email is not valid",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun showPasswordErrorToast(context: Context){
        Toast.makeText(
            context,
            "Password should contain min. 6 characters, one number, uppercase letter and lowercase letter",
            Toast.LENGTH_LONG
        ).show()
    }

    fun login(email: String, password: String, context: Context) {
        // TODO: Implement actual login
        Toast.makeText(
            context,
            "Login is not implemented yet",
            Toast.LENGTH_SHORT
        ).show()

        sendLogin(email, password)
//        navigationManager.navigate(Screen.Dashboard.navigationCommand())
    }

    fun sendLogin(email: String, password: String){
        Network.service.login(email, password).enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
//                _loading.value = false
                response.body()?.let { it ->
//                    _sessionItems.value = it.toMutableList()
                }
                var headers = response.raw().headers()
                for (header in headers.values("set-cookie")) {
                    var lul2 = ""
                }
                // TODO Run through array find set-cookies and save them somehow

                var lul = ""
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
//                _loading.value = false
                t.printStackTrace()
//                _error.value = "Some error occurred while fetching data"
            }
        })
    }
}