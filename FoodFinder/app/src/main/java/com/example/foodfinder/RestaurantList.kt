package com.example.foodfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_restaurant.*


class RestaurantList : Fragment() {

    lateinit var restaurantList : ArrayList<RestaurantEntity> ;

    var foodDb : FoodDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodDb = FoodDatabase.getInstance(context = this.requireContext())
        restaurantList = foodDb?.restaurantDao()!!.getAllRestaurants() as ArrayList<RestaurantEntity>;
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_restaurant, container, false)

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        rList.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RestaurantAdapter(restaurantList);
        }
    }

    companion object {
        fun newInstance(): RestaurantList = RestaurantList()
    }
}