package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum Class that describes our main screens.
 * Corresponding Tab Icons are also stored.
 */
enum class ScreensEnum(val icon: ImageVector) {
    Main( Icons.Filled.Home ),
    Map( Icons.Filled.Place ),
    Settings( Icons.Filled.Settings );
}
