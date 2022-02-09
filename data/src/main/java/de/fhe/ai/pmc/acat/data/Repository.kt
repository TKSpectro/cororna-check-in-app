package de.fhe.ai.pmc.acat.data

import de.fhe.ai.pmc.acat.domain.Repository
import de.fhe.ai.pmc.acat.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(
    private val userEntityDao: UserEntityDao
    ): Repository {

    override fun getEntities(): Flow<List<User>> =
        userEntityDao.getAll()
            .map { entityList ->
                val returnValue = mutableListOf<User>()

                entityList.forEach { entity ->
                    returnValue += entity.toDomain()
                }

                returnValue
            }

    override suspend fun insert(user: User) = userEntityDao.insert( user.fromDomain() )

}