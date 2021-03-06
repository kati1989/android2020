package com.example.foodfinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : FragmentActivity(), AddRestaurant.NotifySaveRestaurant, EditProfile.NotifySaveProfile {
    var foodDb : FoodDatabase? = null;
    var loggedInUserProfile : ProfileEntity?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        setContentView(R.layout.activity_main)
        foodDb = FoodDatabase.getInstance(context = this)
        var shared =applicationContext.getSharedPreferences("test", Context.MODE_PRIVATE)
        var userName =shared.getString("userName", "");
        var password =shared.getString("password", "");

        loggedInUserProfile = foodDb?.profileDao()!!.getProfileByEmail(userName!!);
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        viewModel.selectProfile(loggedInUserProfile)
        // kezdetben a RestaurantList fragmenst toltjuk be a fragment_placeholder ui elemenukbe
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.add(R.id.fragment_placeHolder, RestaurantList())
        ft.commit()
    }

    //editProfil es RestaurantList fragmentek kozotti navigalas gombnyomassal.
    fun switch_fragment(view: View) {
        var newFragment: Fragment? = null
        when (view.id) {
            R.id.btn_edit_profile -> newFragment = EditProfile(this)
            R.id.btn_restaurant_list -> newFragment = RestaurantList()
        }
        setFragment(newFragment)
    }

    //segedfugveny egy fragmens kicserelesere
    fun setFragment(fragment: Fragment?) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        // kicsereljuk a fragment_placeHolder ben levo fragmenset egy uj fragmensre
        fragmentTransaction.replace(R.id.fragment_placeHolder, fragment!!)
        fragmentTransaction.commit()
    }

    fun addRestaurant(view: View) {
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        viewModel.selectRestaurant(null)
        setFragment(AddRestaurant(this))
    }

    fun editRestaurant(view: View) {
        setFragment(AddRestaurant(this));
    }

    fun deleteRestaurant(view: View){
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        var r : RestaurantEntity? =  viewModel.selectedItem.value

        foodDb?.restaurantDao()!!.deleteRestaurant(r!!)
        setFragment(RestaurantList())
    }

    override fun onSaveClicked() {
        //kivesszuk a viewModelbol a vendeglot.
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        var r : RestaurantEntity? =  viewModel.selectedItem.value

        if (r!!.restaurantId<0) // hogyha uj az id ja kissebb mint 0 es inserteljuk
            foodDb?.restaurantDao()!!.insertRestaurant(r!!)
        else
            foodDb?.restaurantDao()!!.updateRestaurant(r!!)
        //mentes utan visszanavigalunk a RestaurantListhez
        setFragment(RestaurantList())
    }

    // hogyha a fragmens jelezte, hogy mentesre van szukseg akkor elmentjuk a viewModelben levo Profilt
    override fun onSaveProfileClicked() {
        //lekerdezzuk a viewModelben levo PRofilt
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        var p : ProfileEntity? =  viewModel.selectedProfile.value
        //perzisztaljuk a profilt.
        foodDb?.profileDao()!!.update(p!!)
        setFragment(RestaurantList())
    }


}