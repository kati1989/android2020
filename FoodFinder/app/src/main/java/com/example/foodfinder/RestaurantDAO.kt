package com.example.foodfinder

import androidx.room.*

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

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurant: RestaurantEntity)

    @Update
    fun updateRestaurant(restaurant: RestaurantEntity)

    @Delete
    fun deleteRestaurant(restaurant: RestaurantEntity)
}
