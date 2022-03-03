package de.fhe.ai.pmc.acat.domain

class GetUsers(private val repository: Repository) {
    operator fun invoke() = repository.getUsers()
}