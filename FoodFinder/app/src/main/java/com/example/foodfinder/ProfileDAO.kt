package com.example.foodfinder

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update


/**
 * Profil adatok adatbazisszintu elereset megvalosito osztaly.
 */
@Dao
interface ProfileDAO {
    //getProfile - lekerdezzuk az osszes profilt.
    @Transaction
    @Query("SELECT * FROM profile")
    fun getProfile(): List<ProfileEntity>

    @Update
    fun update(profile : ProfileEntity?)
}