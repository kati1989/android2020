package com.example.foodfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_restaurant.*


class RestaurantList : Fragment()  , RestaurantAdapter.OnRestaurantListener{

    lateinit var restaurantList : ArrayList<RestaurantEntity> ;

    var foodDb : FoodDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodDb = FoodDatabase.getInstance(context = this.requireContext())
        restaurantList = foodDb?.restaurantDao()!!.getAllRestaurants() as ArrayList<RestaurantEntity>;
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_restaurant, container, false)

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here

        var resAdapter : RestaurantAdapter= RestaurantAdapter(restaurantList, this)
        rList.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = resAdapter
        }
    }

    companion object {
        fun newInstance(): RestaurantList = RestaurantList()
    }

    override fun onRestaurantClick(position: Int) {
        restaurantList.get(position);
        setFragment(fragment = RestaurantDetailFragment(restaurantList.get(position)))
    }

    fun addRestaurant(view: View){
        setFragment(fragment= AddRestaurant())
    }

    fun setFragment(fragment: Fragment?) {
        val fm: FragmentManager? = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_placeHolder, fragment!!)
        fragmentTransaction.commit()
    }


}