package com.example.foodfinder

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ProfileDAO {
    @Transaction
    @Query("SELECT * FROM profile")
    fun getProfile(): List<ProfileEntity>
}