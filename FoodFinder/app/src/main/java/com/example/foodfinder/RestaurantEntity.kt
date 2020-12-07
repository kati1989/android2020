package com.example.foodfinder

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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

    @ColumnInfo(name = "price", defaultValue = "0" )
    @NonNull
    val price: Int,

        @ColumnInfo(name = "title", defaultValue = "")
    @NonNull
    val title: String,

    @PrimaryKey(autoGenerate = true)
    @NonNull
    val restaurantId: Int

)
