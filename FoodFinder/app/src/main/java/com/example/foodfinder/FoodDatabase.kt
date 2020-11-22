package com.example.foodfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProfileEntity::class, Restaurant::class, ProfileRestaurantRef::class), version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        open fun getInstance(context: Context): FoodDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, FoodDatabase::class.java, "FoodDB")
                        .createFromAsset("database/food.db")
                        .build()
            }
            return INSTANCE
        }
    }
}

