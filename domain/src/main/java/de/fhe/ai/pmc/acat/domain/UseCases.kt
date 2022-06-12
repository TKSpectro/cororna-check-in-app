package de.fhe.ai.pmc.acat.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUsers(private val repository: Repository) {
//    operator fun invoke() = repository.getUsers()
}

private const val ASYNC_WAIT_DELAY = 500L

class GetUsersAsync(private val repository: Repository) {
    operator fun invoke(): Flow<AsyncOperation> = flow {

        emit( AsyncOperation.loading("Start loading users...") )

//        repository.getUsers().collect() {
//            delay(ASYNC_WAIT_DELAY)
//            emit( AsyncOperation.success("Users loaded", it ))
//        }

    }
}

class GetSessionsAsync(private val repository: Repository) {
    operator fun invoke(): Flow<AsyncOperation> = flow {

        emit( AsyncOperation.loading("Start loading sessions...") )

//        repository.getSessions().collect() {
//            delay(ASYNC_WAIT_DELAY)
//            emit( AsyncOperation.success("Sessions loaded", it ))
//        }

    }
}