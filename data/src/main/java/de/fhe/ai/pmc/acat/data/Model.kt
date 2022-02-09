package de.fhe.ai.pmc.acat.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val text: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0
)

