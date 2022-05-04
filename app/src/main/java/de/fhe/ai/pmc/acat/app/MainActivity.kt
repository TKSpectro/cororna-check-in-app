package de.fhe.ai.pmc.acat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidCleanArchTemplateTheme {
        Greeting("Android")
    }
}
