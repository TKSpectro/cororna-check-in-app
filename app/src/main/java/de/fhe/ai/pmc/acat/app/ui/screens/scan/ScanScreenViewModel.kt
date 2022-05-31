package de.fhe.ai.pmc.acat.app.ui.screens.scan

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import de.fhe.ai.pmc.acat.app.ui.screens.core.NavigationManager

class ScanScreenViewModel(private val navigationManager: NavigationManager) : ViewModel() {
    fun showQRResult(context: Context, text: String) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_LONG
        ).show()
    }

    fun showPermissionNotGivenError(context: Context) {
        Toast.makeText(
            context,
            "You need to give camera permissions to scan. Please go to the app settings and enable them.",
            Toast.LENGTH_LONG
        ).show()
    }
}
