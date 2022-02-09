package de.fhe.ai.pmc.acat.domain

class GetEntities(private val repository: Repository) {
    operator fun invoke() = repository.getEntities()
}