package com.example.foodfinder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_add_restaurant.*

class MainActivity : FragmentActivity() {

    var foodDb : FoodDatabase? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        foodDb = FoodDatabase.getInstance(context = this)

        // kezdetben a RestaurantList fragmenst toltjuk be a fragment_placeholder ui elemenukbe
        ft.add(R.id.fragment_placeHolder, RestaurantList())
        ft.commit()
    }

    //editProfil es RestaurantList fragmentek kozotti navigalas gombnyomassal.
    fun switch_fragment(view: View) {
        var newFragment: Fragment? = null
        when (view.id) {
            R.id.btn_edit_profile -> newFragment = EditProfile()
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
        setFragment(AddRestaurant())
    }

    fun storeRestaurant(view: View) {
        var price : Int = 1
        if (radioButton.isSelected)
            price =1
        if (radioButton2.isSelected)
            price =2
        if (radioButton3.isSelected)
            price =3
        if (radioButton4.isSelected)
            price = 4

        var  restaurant : RestaurantEntity = RestaurantEntity("",edit_address_restaurant.text.toString(),
                price,edit_title_restaurant.text.toString(),0);
        foodDb?.restaurantDao()!!.insertRestaurant(restaurant);
        setFragment(RestaurantList())
    }


}