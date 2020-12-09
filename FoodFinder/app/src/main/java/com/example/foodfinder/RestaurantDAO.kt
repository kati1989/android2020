package com.example.foodfinder

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

/**
 * Restaurant adatok adatbazisszintu elereset megvalosito osztaly.
 */
@Dao
interface RestaurantDao {
    /**
     * getAllRestaurants - lekerdezzuk az osszes Restaurant Entitast.
     */
    @Transaction
    @Query("SELECT * from restaurant")
    fun getAllRestaurants() : List<RestaurantEntity>
}
