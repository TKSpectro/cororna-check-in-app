package de.fhe.ai.pmc.acat.data

import de.fhe.ai.pmc.acat.domain.Repository
import de.fhe.ai.pmc.acat.domain.User
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val userEntityDao: UserEntityDao
    ): Repository {

    override fun getUsers() =
        userEntityDao.getAll()
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
}