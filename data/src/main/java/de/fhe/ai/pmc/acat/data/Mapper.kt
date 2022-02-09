package de.fhe.ai.pmc.acat.data

import de.fhe.ai.pmc.acat.domain.User

fun UserEntity.toDomain() = User(this.text, this.id)
fun User.fromDomain() = UserEntity(this.name, this.id)
