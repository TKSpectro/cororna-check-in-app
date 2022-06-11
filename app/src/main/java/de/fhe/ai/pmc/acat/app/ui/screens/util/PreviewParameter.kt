package de.fhe.ai.pmc.acat.app.ui.screens.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import de.fhe.ai.pmc.acat.domain.AsyncOperation
import de.fhe.ai.pmc.acat.domain.Session
import de.fhe.ai.pmc.acat.domain.User
import java.time.LocalDateTime
import java.util.*

class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values = userList.asSequence()
}

class UserListPreviewParameterProvider : PreviewParameterProvider<List<User>> {
    override val values = sequenceOf(userList)
}

class SessionPreviewParameterProvider : PreviewParameterProvider<Session>{
    override val values = sessionList.asSequence()
}

class SessionListPreviewParameterProvider: PreviewParameterProvider<List<Session>>{
    override val values = sequenceOf(sessionList)
}

class AsyncOpPreviewParameterProvider : PreviewParameterProvider<AsyncOperation> {
    override val values = asyncOpList.asSequence()
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

val date: LocalDateTime = LocalDateTime.now()
private val sessionList = listOf<Session>(
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", "", "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", "", "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", "", "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", "", "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", "", "123", "")
)

private val asyncOpList = listOf(
    AsyncOperation.undefined(),
    AsyncOperation.loading(),
    AsyncOperation.error(),
    AsyncOperation.saving(),
    AsyncOperation.success(payload = "Payload")
)

const val PREVIEW_BACKGROUND_COLOR = 0xbbbbbbbb
