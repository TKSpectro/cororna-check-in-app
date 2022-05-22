package de.fhe.ai.pmc.acat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userEntityDao(): UserEntityDao

    companion object {
        var db: AppDatabase? = null

        private fun getDatabase(app: Context): AppDatabase {
            if( db == null ) {
                db = Room.databaseBuilder( app, AppDatabase::class.java, "app-db")
                .fallbackToDestructiveMigration()
                .build()
            }

            return db as AppDatabase
        }

        fun getUserEntityDao(app: Context): UserEntityDao {
            return getDatabase(app).userEntityDao()
        }
    }
}

