package com.example.foodfinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantViewModel : ViewModel() {
    private val mutableSelectedItem = MutableLiveData<RestaurantEntity>()
    val selectedItem: LiveData<RestaurantEntity> get() = mutableSelectedItem

    fun selectItem(item: RestaurantEntity?) {
        mutableSelectedItem.value = item
    }


}