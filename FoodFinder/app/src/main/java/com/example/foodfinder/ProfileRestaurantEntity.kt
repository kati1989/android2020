package com.example.foodfinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_restaurant", primaryKeys = ["playlistId", "songId"])
class ProfileRestaurantEntity(
    @ColumnInfo(name = "profile") val profileId: Int,
    @ColumnInfo(name = "restaurant") val restaurantId: Int
)