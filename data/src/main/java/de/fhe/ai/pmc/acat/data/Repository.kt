package de.fhe.ai.pmc.acat.data

import de.fhe.ai.pmc.acat.domain.Repository
import de.fhe.ai.pmc.acat.domain.Room
import de.fhe.ai.pmc.acat.domain.Session
import de.fhe.ai.pmc.acat.domain.User
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.util.*

class RepositoryImpl(
    private val userEntityDao: UserEntityDao
    ): Repository {
}
