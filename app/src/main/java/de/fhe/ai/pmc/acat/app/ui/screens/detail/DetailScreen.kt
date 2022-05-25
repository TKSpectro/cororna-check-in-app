package de.fhe.ai.pmc.acat.app.ui.screens.detail

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.inject
import org.koin.androidx.compose.viewModel

val DetailScreenAppBarActions: @Composable RowScope.() -> Unit = {
    val vm by viewModel<DetailScreenViewModel>()
    val context = LocalContext.current

    IconButton(
        onClick = {
            vm.share(context)
        }
    ) {
        Icon(Icons.Filled.Share, contentDescription = null)
    }
}

@Composable
fun DetailScreen( userId: Long? ) {
    val vm by viewModel<DetailScreenViewModel>()
    vm.userId = userId!!

    Text("Detail: $userId")

    println(vm)
}
