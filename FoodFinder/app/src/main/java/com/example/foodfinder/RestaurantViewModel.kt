package com.example.foodfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// tarolja a Profilt illetve Retaurantot amin modositasokat akarunk vegrehajtani
//Fragment - Activity, Fragment -Fragment kozotti adat kommunikaciora hasznaljuk
class RestaurantViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<RestaurantEntity>()
    val selectedItem: LiveData<RestaurantEntity> get() = mutableSelectedItem

    private val mutableProfile = MutableLiveData<ProfileEntity>()
    val selectedProfile: MutableLiveData<ProfileEntity> get() = mutableProfile

    //beallitjuk a kivalasztott RestaurantEntity
    fun selectRestaurant(item: RestaurantEntity?) {
        mutableSelectedItem.value = item
    }

    //beallitjuk a kivalasztott ProfileEntity
    fun selectProfile(item : ProfileEntity?) {
        mutableProfile.value = item
    }


}