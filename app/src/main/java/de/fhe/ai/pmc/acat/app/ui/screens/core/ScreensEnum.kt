package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum Class that describes our screens.
 * Corresponding Tab/Action Bar Icons are also stored as well as
 * (optional) Action Bar Actions.
 */
enum class ScreensEnum(
    val icon: ImageVector = Icons.Filled.Favorite,
    val isRootScreen: Boolean = false,
    val actions: @Composable RowScope.() -> Unit = {}
    ) {

    // Users
    Main(
        Icons.Filled.Home,
        true,
        actions = {
            IconButton( onClick = { println("Add User") } ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ),
    UserDetail(
        Icons.Filled.AccountBox,
        actions = {
            IconButton( onClick = { println("Remove User") } ) {
                Icon(Icons.Filled.Delete, contentDescription = null)
            }
        }
    ),

    // Map
    Map(
        Icons.Filled.Place,
        true,
        actions = {
            IconButton( onClick = { println("Map Mode") } ) {
                Icon(Icons.Filled.Build, contentDescription = null)
            }
        }
    ),

    // Settings
    Settings(
        Icons.Filled.Settings,
        true
    );
}
