package com.example.foodfinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "address") val adress: String,

    @ColumnInfo(name = "profile_pic") val profilePic: String,

    @ColumnInfo(name = "email") val email: String
)

