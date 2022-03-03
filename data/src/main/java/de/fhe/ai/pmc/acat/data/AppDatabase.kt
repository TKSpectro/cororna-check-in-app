package de.fhe.ai.pmc.acat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.fhe.ai.pmc.acat.domain.Repository

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userEntityDao(): UserEntityDao
}


fun init(app: Context): Repository {
    val db = Room.databaseBuilder( app, AppDatabase::class.java, "app-db")
        .fallbackToDestructiveMigration()
        .build()

    return RepositoryImpl( db.userEntityDao() )
}