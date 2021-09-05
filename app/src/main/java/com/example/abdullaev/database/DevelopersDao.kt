package com.example.abdullaev.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abdullaev.networkutils.pojo.DeveloperInfo
import java.util.concurrent.Flow

@Dao
interface DevelopersDao {
    @Query("SELECT * FROM developer")
    fun getAllDevelopers(): List<DeveloperInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeveloper(developer: DeveloperInfo)

    @Query("SELECT * FROM developer WHERE id = :id")
    fun getDeveloperInfoById(id: Int): DeveloperInfo

    @Query("DELETE FROM developer")
    fun deleteAll()
}