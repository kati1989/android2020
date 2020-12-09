package com.example.foodfinder

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RestaurantDao {
   /* @Transaction
    @Query("SELECT * FROM favorite_restaurant")
    fun getProfileFavoriteRestaurants(): List<ProfileFavoriteRestaurants>
*/
    @Transaction
    @Query("SELECT * from restaurant")
    fun getAllRestaurants() : List<RestaurantEntity>
}
