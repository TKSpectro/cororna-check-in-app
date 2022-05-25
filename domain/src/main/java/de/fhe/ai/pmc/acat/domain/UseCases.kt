package de.fhe.ai.pmc.acat.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsers(private val repository: Repository) {
    operator fun invoke() = repository.getUsers()
}

class GetUsersAsync(private val repository: Repository) {
    operator fun invoke(): Flow<AsyncOperation> = flow {

        emit( AsyncOperation.loading("Start loading users...") )

        repository.getUsers().collect() {
            delay(500)
            emit( AsyncOperation.success("Users loaded", it ))
        }

    }
}