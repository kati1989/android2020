package com.example.foodfinder

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*

class MainActivity : FragmentActivity(), AddRestaurant.NotifySaveRestaurant, EditProfile.NotifySaveProfile {

    var foodDb : FoodDatabase? = null;
    var loggedInUserProfile : ProfileEntity?= null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        setContentView(R.layout.activity_main)
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        foodDb = FoodDatabase.getInstance(context = this)
        var profileList : List<ProfileEntity> = foodDb?.profileDao()!!.getProfile() as ArrayList<ProfileEntity>;
        loggedInUserProfile = profileList.get(0)
        // kezdetben a RestaurantList fragmenst toltjuk be a fragment_placeholder ui elemenukbe
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

    fun editRestaurant(view : View) {
        setFragment(AddRestaurant(this));
    }

    fun deleteRestaurant(view : View){
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        var r : RestaurantEntity? =  viewModel.selectedItem.value

        foodDb?.restaurantDao()!!.deleteRestaurant(r!!)
        setFragment(RestaurantList())
    }

    override fun onSaveClicked() {
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        var r : RestaurantEntity? =  viewModel.selectedItem.value

        if (r!!.restaurantId<0)
            foodDb?.restaurantDao()!!.insertRestaurant(r!!)
        else
            foodDb?.restaurantDao()!!.updateRestaurant(r!!)

        setFragment(RestaurantList())
    }

    override fun onSaveProfileClicked() {
        val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>()
        var p : ProfileEntity? =  viewModel.selectedProfile.value
        foodDb?.profileDao()!!.update(p!!)

        setFragment(RestaurantList())
    }


}