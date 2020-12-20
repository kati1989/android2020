package com.example.foodfinder

import androidx.annotation.NonNull
import androidx.room.*
import java.lang.reflect.Constructor


/**
 * Entity class for modelling a Restaurant
 */
@Entity(tableName = "restaurant")
class RestaurantEntity(
        @ColumnInfo(name = "image", defaultValue = "")
        @NonNull
        val image: String,

        @ColumnInfo(name = "address", defaultValue = "")
        @NonNull
        val adress: String,

        @ColumnInfo(name = "price", defaultValue = "0")
        @NonNull
        val price: Int,

        @ColumnInfo(name = "title", defaultValue = "")
        @NonNull
        val title: String,

        @PrimaryKey(autoGenerate = true)
        @NonNull
        val restaurantId: Int,

        @ColumnInfo(name = "lat", defaultValue = "0")
        @NonNull
        val lat: Double,

        @ColumnInfo(name = "long", defaultValue = "0")
        @NonNull
        val longitude: Double
){
        @Ignore
        var isFavoriteForActualProfile: Boolean = false
}


