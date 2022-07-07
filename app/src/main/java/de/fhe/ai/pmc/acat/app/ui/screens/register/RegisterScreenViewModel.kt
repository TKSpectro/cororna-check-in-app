package de.fhe.ai.pmc.acat.app.ui.screens.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen

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
        Toast.makeText(
            context,
            firstName + " " + lastName + " " + email + " " + password,
            Toast.LENGTH_LONG
        ).show()
    }


}