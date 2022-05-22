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
    var actions: @Composable RowScope.() -> Unit = {},
    ) {

    // Users
    Main(
        Icons.Filled.Home,
        true
    ),
    UserDetail(
        Icons.Filled.AccountBox
    ),

    // Map
    Map(
        Icons.Filled.Place,
        true
    ),

    // Settings
    Settings(
        Icons.Filled.Settings,
        true
    );
}
