package com.example.foodfinder

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RestaurantDao {
    @Transaction
    @Query("SELECT * FROM ProfileEntity")
    fun getProfileFavoriteRestaurants(): List<ProfileFavoriteRestaurants>

    @Transaction
    @Query("SELECT * FROM Restaurant")
    fun getAllRestaurants() : List<Restaurant>
}
