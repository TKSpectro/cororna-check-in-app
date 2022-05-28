package de.fhe.ai.pmc.acat.app.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager
import de.fhe.ai.pmc.acat.app.ui.screens.core.Screen

class DashboardScreenViewModel(
    private val navigationManager: NavigationManager
) : ViewModel() {
    fun navigateToSessionList() {
         navigationManager.navigate( Screen.SessionList.navigationCommand() )
    }
}
