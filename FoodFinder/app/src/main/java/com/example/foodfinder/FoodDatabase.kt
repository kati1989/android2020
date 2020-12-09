package com.example.foodfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Adatbazis inicializalo osztaly mely segitsegevel elerjuk a Profile Restaurant
 * illetve ProfileRestaurantRef(favorites) entitasokat
 */
@Database(entities = arrayOf(ProfileEntity::class,
        RestaurantEntity::class, ProfileRestaurantRef::class), version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao  // a restaurantDao ban talalhatoak a restaurant tabla muveletei

    abstract fun profileDao(): ProfileDAO // a profileDao ban talalhatoak a profile tabla muveletei

    companion object {
        //singleton patternel csak egyszer hozzuk letre az adatbazis osztalyunkat
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        open fun getInstance(context: Context): FoodDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, FoodDatabase::class.java, "foodstore.db") //megadjuk a nevet
                        .createFromAsset("database/sqlite2.db") // megadjuk hogy melyik filebol epitse az adatbazist
                        .allowMainThreadQueries() // lehessen a main szallon adatbazis lekerdezeseket futtatni
                        .build()
            }
            return INSTANCE
        }
    }
}

