package com.example.foodfinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val profileId: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "address") val address: String,

    @ColumnInfo(name = "profile_pic") val profilePic: String,

    @ColumnInfo(name = "email") val email: String
)

