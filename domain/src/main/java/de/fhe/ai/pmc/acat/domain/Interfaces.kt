package de.fhe.ai.pmc.acat.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUsers(): Flow<List<User>>
    suspend fun getUser( userId: Long ) : User?
    suspend fun insertUser( user: User ): Long
    suspend fun updateUser( user: User ): Long // Check Return Type

    fun getSessions(): Flow<List<Session>>
}

interface Logger {
    fun error( message: String )
    fun info( message: String )
}
