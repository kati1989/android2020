package com.example.foodfinder

import androidx.room.Dao
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM profile_restaurant")
    fun getProfileFavoriteRestaurants(): List<ProfileFavoriteRestaurants>

    @Query("SELECT * FROM restaurant_table")
    fun getAllRestaurants() : List<Restaurant>
}
