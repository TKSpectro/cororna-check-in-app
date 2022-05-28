package de.fhe.ai.pmc.acat.app.ui.screens.auth

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen

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

        navigationManager.navigate(Screen.Dashboard.navigationCommand())
    }
}