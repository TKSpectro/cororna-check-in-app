package de.fhe.ai.pmc.acat.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEntityDao {

    @Query("SELECT * FROM UserEntity")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    suspend fun get(id: Long): UserEntity?

    @Insert
    suspend fun insert(entity: UserEntity): Long
}
