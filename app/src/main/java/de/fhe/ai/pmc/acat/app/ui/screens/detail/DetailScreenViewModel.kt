package de.fhe.ai.pmc.acat.app.ui.screens.detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel

class DetailScreenViewModel() : ViewModel() {

    var userId: Long = 0

    fun share(context: Context) {

        println( this )

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send - $userId.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "My Share Intent")
        context.startActivity(shareIntent)
    }
}
