package com.example.foodfinder

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Entity class for modelling a Profile
 */
@Entity( tableName = "profile")
class ProfileEntity(
        @PrimaryKey(autoGenerate = true) val profileId: Int,

        @ColumnInfo(name = "name", defaultValue = "")
        @NonNull
        val name: String,

        @ColumnInfo(name = "address", defaultValue = "")
        @NonNull
        val address: String,

        @ColumnInfo(name = "profile_pic", defaultValue = "")
        @NonNull
        val profilePic: String,

        @ColumnInfo(name = "email", defaultValue = "")
        @NonNull
        val email: String,

        @ColumnInfo(name = "phone", defaultValue = "")
        @NonNull
        val phone: String
)

