package com.example.abdullaev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.abdullaev.networkutils.pojo.DeveloperInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [DeveloperInfo::class], version = 1, exportSchema = false)
abstract class DevelopersDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: DevelopersDatabase? = null
        private val DB_NAME: String = "notes.db"

        fun getInstance(context: Context): DevelopersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DevelopersDatabase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun developersDao(): DevelopersDao
}