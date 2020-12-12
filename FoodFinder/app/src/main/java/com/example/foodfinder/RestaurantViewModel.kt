package com.example.foodfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<RestaurantEntity>()
    val selectedItem: LiveData<RestaurantEntity> get() = mutableSelectedItem

    private val mutableProfile = MutableLiveData<ProfileEntity>()
    val selectedProfile: MutableLiveData<ProfileEntity> get() = mutableProfile

    fun selectRestaurant(item: RestaurantEntity?) {
        mutableSelectedItem.value = item
    }

    fun selectProfile(item : ProfileEntity?) {
        mutableProfile.value = item
    }


}