package com.example.foodfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ProfileEntity::class,
        RestaurantEntity::class, ProfileRestaurantRef::class), version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun profileDao(): ProfileDAO

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        open fun getInstance(context: Context): FoodDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, FoodDatabase::class.java, "foodstore.db")
                        .createFromAsset("database/sqlite2.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }
    }
}

