package de.fhe.ai.pmc.acat.app.ui.screens.detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel

class DetailScreenViewModel(val userId: Long) : ViewModel() {

    fun share(context: Context) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send: UserID $userId.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "My Share Intent")
        context.startActivity(shareIntent)
    }
}
