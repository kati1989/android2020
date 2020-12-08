package com.example.foodfinder

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : FragmentActivity() {

    var foodDb : FoodDatabase? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodDb = FoodDatabase.getInstance(context = this)
        setContentView(R.layout.activity_main)

        // set desired fragment for the first time
        // set desired fragment for the first time
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        ft.add(R.id.fragment_placeHolder, RestaurantList())
        ft.commit()
    }

    fun switch_fragment(view: View) {
        var newFragment: Fragment? = null
        when (view.id) {
            R.id.btn_1 -> newFragment = EditProfile()
            R.id.btn_2 -> newFragment = RestaurantList()
        }
        setFragment(newFragment)
    }

    fun setFragment(fragment: Fragment?) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_placeHolder, fragment!!)
        fragmentTransaction.commit()
    }
}