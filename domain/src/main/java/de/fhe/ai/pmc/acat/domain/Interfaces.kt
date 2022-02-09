package de.fhe.ai.pmc.acat.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getEntities(): Flow<List<User>>
    suspend fun insert( user: User ): Long
}

interface Logger {
    fun error( message: String )
    fun info( message: String )
}