package de.fhe.ai.pmc.acat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import de.fhe.ai.pmc.acat.android_core.LoggerImpl
import de.fhe.ai.pmc.acat.data.init
import de.fhe.ai.pmc.acat.domain.User
import de.fhe.ai.pmc.acat.ui.theme.AndroidCleanArchTemplateTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCleanArchTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val repo = init( this )

        val dbScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        val logger = LoggerImpl()

        dbScope.launch {

            repo.insert(User("steffen"))

            repo.getEntities().collect {
                logger.info( it.toString() )
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
