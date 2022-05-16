package de.fhe.ai.pmc.acat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import de.fhe.ai.pmc.acat.app.ui.screens.core.AppScaffold
import de.fhe.ai.pmc.acat.app.ui.theme.AndroidCleanArchTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidCleanArchTemplateTheme {
                Surface( color = MaterialTheme.colors.background ) {
                    AppScaffold()
                }
            }
        }

    }
}
