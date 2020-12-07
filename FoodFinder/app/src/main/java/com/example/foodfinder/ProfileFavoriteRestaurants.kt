package com.example.foodfinder

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ProfileFavoriteRestaurants(
    @Embedded val profile: ProfileEntity,
    @Relation(
        parentColumn = "profileId",
        entityColumn = "restaurantId",
        associateBy = Junction(ProfileRestaurantRef::class)
    )
    val restaurants: List<RestaurantEntity>
)