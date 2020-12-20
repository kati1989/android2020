package com.example.foodfinder

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Entitas osztaly a profile adatbazistabla modellezesere.
 */
@Entity( tableName = "profile")
class ProfileEntity(
        @PrimaryKey(autoGenerate = true) val profileId: Int,

        @ColumnInfo(name = "name", defaultValue = "") // oszlop neve :"name", alapertelmezett erteke =""
        @NonNull // - nem lehet null.
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
        val phone: String,

        @ColumnInfo(name = "password", defaultValue = "")
        @NonNull
        val password: String
)

