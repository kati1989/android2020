package com.example.foodfinder

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
//Relacios tabla mely osszekoti a Profile-t a Restaurantal kedvenc vendeglok meghatarozasara
// (nem biztos jo igy)
data class ProfileFavoriteRestaurants(
    @Embedded val profile: ProfileEntity,
    @Relation(
        parentColumn = "profileId",
        entityColumn = "restaurantId",
        associateBy = Junction(ProfileRestaurantRef::class)
    )
    val restaurants: List<RestaurantEntity>
)