package com.example.foodfinder

import android.provider.ContactsContract
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ProfileFavoriteRestaurants(
    @Embedded val profile: ProfileEntity,
    @Relation(
        parentColumn = "profileId",
        entityColumn = "restaurantId",
        associateBy = Junction(ProfileRestaurantEntity::class)
    )
    val playlists: List<Restaurant>
)