package com.example.foodfinder

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(tableName = "favorite_restaurant", primaryKeys = ["profileId", "restaurantId"])
class ProfileRestaurantRef (
        @ColumnInfo(name = "profileId") val profileId: Int,
        @ColumnInfo(name = "restaurantId") val restaurantId: Int
)
