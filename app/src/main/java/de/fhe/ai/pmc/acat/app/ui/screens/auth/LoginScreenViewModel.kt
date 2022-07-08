package de.fhe.ai.pmc.acat.app.ui.screens.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.LoginBody
import de.fhe.ai.pmc.acat.domain.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginScreenViewModel(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun navigateToRegisterScreen() {
        navigationManager.navigate(Screen.Register.navigationCommand())
    }

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
        sendLogin(email, password, context)
    }

    private fun sendLogin(email: String, password: String, context: Context){
        val body = LoginBody(email, password)

        Network.service.login(body).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.let { it ->
                    // Write token to the sharedPreferences
                    val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("auth_token", it.token)
                    editor.apply()

                    navigationManager.navigate(Screen.Dashboard.navigationCommand())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Login was not valid.",
                    Toast.LENGTH_SHORT
                ).show()

                t.printStackTrace()
            }
        })
    }
}