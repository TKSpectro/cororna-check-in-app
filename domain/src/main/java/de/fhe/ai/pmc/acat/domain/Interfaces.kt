package de.fhe.ai.pmc.acat.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getEntities(): Flow<List<Entity>>
}

interface Logger {
    fun error( message: String )
    fun info( message: String )
}