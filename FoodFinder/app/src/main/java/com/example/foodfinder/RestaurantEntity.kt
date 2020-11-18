package com.example.foodfinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class for modelling a Restaurant
 */
@Entity(tableName = "restaurant_table")
class Restaurant(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "address") val adress: String,

    @ColumnInfo(name = "image") val image: String,

    @ColumnInfo(name = "price") val price: Int
)
