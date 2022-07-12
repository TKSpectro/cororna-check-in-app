package de.fhe.ai.pmc.acat.app.ui.screens.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import de.fhe.ai.pmc.acat.domain.*
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

class RoomPreviewParameterProvider : PreviewParameterProvider<Room>{
    override val values = roomList.asSequence()
}

class AsyncOpPreviewParameterProvider : PreviewParameterProvider<AsyncOperation> {
    override val values = asyncOpList.asSequence()
}

private val emptySessionList: List<Session> = listOf()
private val emptyInfectionList: List<Infection> = listOf()

private val userList = listOf(
    User("123", "John", "Doe", "jdoe", "JDOE", "j.doe@fh-erfurt.de", "J.DOE@fh-erfurt.de", true, "", "", "", "", false, false, "", false, 0, emptySessionList, emptyInfectionList),
    User("1232", "John2", "Doe2", "jdoe2", "JDOE2", "j.doe2@fh-erfurt.de", "J.DOE2@fh-erfurt.de", true, "", "", "", "", false, false, "", false, 0, emptySessionList, emptyInfectionList),
    User("1233", "John3", "Doe3", "jdoe3", "JDOE3", "j.doe3@fh-erfurt.de", "J.DOE3@fh-erfurt.de", true, "", "", "", "", false, false, "", false, 0, emptySessionList, emptyInfectionList),
    User("1234", "John4", "Doe4", "jdoe4", "JDOE4", "j.doe4@fh-erfurt.de", "J.DOE4@fh-erfurt.de", true, "", "", "", "", false, false, "", false, 0, emptySessionList, emptyInfectionList),
)

val date: LocalDateTime = LocalDateTime.now()
private val room = Room(UUID.randomUUID().toString(), "Room 1", 10, 90, 1, "", "")
private val sessionList = listOf<Session>(
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", room, "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", room, "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", room, "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", room, "123", ""),
    Session(UUID.randomUUID().toString(), LocalDateTime.now(), LocalDateTime.now(), false, "123", room, "123", "")
)
private val roomList = listOf<Room>(
    Room(UUID.randomUUID().toString(), "1.2.3", 25, 90, 1, "", ""),
    Room(UUID.randomUUID().toString(), "1.2.3", 25, 90, 1, "", ""),
    Room(UUID.randomUUID().toString(), "1.2.3", 25, 90, 1, "", ""),
    Room(UUID.randomUUID().toString(), "1.2.3", 25, 90, 1, "", ""),
    Room(UUID.randomUUID().toString(), "1.2.3", 25, 90, 1, "", "")
)

private val asyncOpList = listOf(
    AsyncOperation.undefined(),
    AsyncOperation.loading(),
    AsyncOperation.error(),
    AsyncOperation.saving(),
    AsyncOperation.success(payload = "Payload")
)

const val PREVIEW_BACKGROUND_COLOR = 0xbbbbbbbb
