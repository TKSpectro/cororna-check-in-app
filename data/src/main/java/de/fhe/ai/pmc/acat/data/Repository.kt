package de.fhe.ai.pmc.acat.data

import de.fhe.ai.pmc.acat.domain.Repository
import de.fhe.ai.pmc.acat.domain.Session
import de.fhe.ai.pmc.acat.domain.User
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.util.*

class RepositoryImpl(
    private val userEntityDao: UserEntityDao
    ): Repository {

    override fun getUsers() =
        userEntityDao.getAllAsFlow()
            .map { entityList ->
                val returnValue = mutableListOf<User>()

                entityList.forEach { entity ->
                    returnValue += entity.toDomain()
                }

                returnValue
            }

    override suspend fun getUser(userId: Long) = userEntityDao.get( userId )?.toDomain()

    override suspend fun insertUser(user: User) = userEntityDao.insert( user.fromDomain() )

    override suspend fun updateUser(user: User): Long {
        TODO("Not yet implemented")
    }

    private val date: LocalDateTime = LocalDateTime.now()
    private val sessionList = listOf<Session>(
        Session(UUID.randomUUID().toString(), "Room1", date, date),
        Session(UUID.randomUUID().toString(), "Room2", date, date),
        Session(UUID.randomUUID().toString(), "Room3", date, date),
        Session(UUID.randomUUID().toString(), "Room4", date, date),
        Session(UUID.randomUUID().toString(), "Room5", date, date)
    )

    override fun getSessions() = flowOf(sessionList)
}
