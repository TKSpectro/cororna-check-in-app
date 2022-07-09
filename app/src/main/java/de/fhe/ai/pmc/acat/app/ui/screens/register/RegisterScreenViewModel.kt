package de.fhe.ai.pmc.acat.app.ui.screens.register

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.network.Network
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen
import de.fhe.ai.pmc.acat.domain.AuthResponse
import de.fhe.ai.pmc.acat.domain.RegisterBody
import retrofit2.Response
import retrofit2.Callback
import retrofit2.Call


class RegisterScreenViewModel (
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun navigateToLoginScreen() {
        navigationManager.navigate(Screen.Login.navigationCommand())
    }

    fun showInputErrorToast(context: Context){
        Toast.makeText(
            context,
            "Please enter all input fields",
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
    fun register(firstName: String, lastName: String, email: String, password: String, context: Context) {
        sendAccountData(firstName, lastName, email, password, context)
    }

    fun sendAccountData(firstName: String, lastName: String,email: String, password: String, context: Context){
        val body = RegisterBody(firstName, lastName, email, password)

        Network.service.register(body).enqueue(object: Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.i("onResponseRq", response.message())
                Log.i("onResponseBody", response.toString())
                response.body()?.let { it ->
                    // Write token to the sharedPreferences
                    val sharedPref = context.getSharedPreferences("ccn", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("auth_token", it.token)
                    editor.apply()

                    navigationManager.navigate(Screen.Dashboard.navigationCommand())
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Register was not valid.",
                    Toast.LENGTH_SHORT
                ).show()

                t.printStackTrace()
            }
        })
    }


}