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

    @Query("SELECT * from favorite_restaurant WHERE profileId = :profileId")
    fun getFavoriteRestaurantForProfile(profileId : Int) : List<ProfileRestaurantRef>

    @Delete
    fun deleteFavorite(profileRestaurantRef: ProfileRestaurantRef)

    @Insert
    fun insertFavorite(profileRestaurantRef: ProfileRestaurantRef)

}
