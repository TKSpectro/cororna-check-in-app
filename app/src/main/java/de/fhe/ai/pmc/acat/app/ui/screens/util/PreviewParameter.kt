package de.fhe.ai.pmc.acat.app.ui.screens.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import de.fhe.ai.pmc.acat.domain.User

class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values = userList.asSequence()
}

class UserListPreviewParameterProvider : PreviewParameterProvider<List<User>> {
    override val values = sequenceOf(userList)
}

private val userList = listOf(
    User("Max"),
    User("User with a really really very unbelievable long first name"),
    User("Siegfried"),
    User("User with a really really very unbelievable long first name, yes a User with a " +
            "really really very unbelievable long first name"),
    User("Another first name"),
    User("Well, yes another one"),
)

const val PREVIEW_BACKGROUND_COLOR = 0xbbbbbbbb
