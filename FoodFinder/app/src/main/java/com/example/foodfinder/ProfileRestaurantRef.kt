package com.example.foodfinder

import androidx.room.Entity


@Entity(primaryKeys = ["profileId", "restaurantId"])
class ProfileRestaurantRef (
   val profileId: Int,
   val restaurantId: Int
)
