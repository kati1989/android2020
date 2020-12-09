package com.example.foodfinder

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

/**
 * Profil adatok adatbazisszintu elereset megvalosito osztaly.
 */
@Dao
interface ProfileDAO {
    //getProfile - lekerdezzuk az osszes profilt.
    @Transaction
    @Query("SELECT * FROM profile")
    fun getProfile(): List<ProfileEntity>
}