package com.example.foodfinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey


//ProfileRestaurantRef entitas kedvenc vendeglok kapcsolat modellezesere
@Entity(tableName = "favorite_restaurant", primaryKeys = [ "restaurantId", "profileId"],
        foreignKeys = arrayOf(ForeignKey(entity = RestaurantEntity::class,
                parentColumns = arrayOf("restaurantId"),
                childColumns = arrayOf("restaurantId"),
                onDelete = ForeignKey.NO_ACTION,
                onUpdate = ForeignKey.NO_ACTION),
                ForeignKey(entity = ProfileEntity::class,
                        parentColumns = arrayOf("profileId"),
                        childColumns = arrayOf("profileId"),
                        onDelete = ForeignKey.NO_ACTION,
                        onUpdate = ForeignKey.NO_ACTION)))
class ProfileRestaurantRef (
        @ColumnInfo(name = "restaurantId" ) val restaurantId: Int,
        @ColumnInfo(name = "profileId") val profileId: Int
)
