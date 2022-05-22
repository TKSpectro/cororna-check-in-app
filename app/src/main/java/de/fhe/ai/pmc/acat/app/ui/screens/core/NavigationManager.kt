package de.fhe.ai.pmc.acat.app.ui.screens.core

import androidx.navigation.NamedNavArgument
import kotlinx.coroutines.flow.MutableStateFlow

interface NavigationCommand {
    val destination: String
    val arguments: List<NamedNavArgument>
}

val DefaultDestination = object : NavigationCommand {
    override val arguments = emptyList<NamedNavArgument>()
    override val destination = ""
}

class NavigationManager {
    var commands = MutableStateFlow(DefaultDestination)

    fun navigate( directions: NavigationCommand ) {
        commands.value = directions
    }
}