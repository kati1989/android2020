package com.example.foodfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProfileEntity::class, Restaurant::class), version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    var instance: FoodDatabase? = null

    abstract fun restaurantDao(): RestaurantDao

    open fun getInstance(context: Context): FoodDatabase? {
        if (instance == null) {
            instance = Room.databaseBuilder(context, FoodDatabase::class.java, "FoodDB")
                .createFromAsset("database/food.db")
                .build()
        }
        return instance
    }
}

