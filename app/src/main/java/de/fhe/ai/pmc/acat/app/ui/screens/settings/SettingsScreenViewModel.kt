package de.fhe.ai.pmc.acat.app.ui.screens.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen

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
}