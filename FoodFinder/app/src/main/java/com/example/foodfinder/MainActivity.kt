package com.example.foodfinder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
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
}