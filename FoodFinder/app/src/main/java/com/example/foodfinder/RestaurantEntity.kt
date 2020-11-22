package com.example.foodfinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class for modelling a Restaurant
 */
@Entity
class Restaurant(

    @PrimaryKey(autoGenerate = true) val restaurantId: Int,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "address") val adress: String,

    @ColumnInfo(name = "image") val image: String,

    @ColumnInfo(name = "price") val price: Int
)
