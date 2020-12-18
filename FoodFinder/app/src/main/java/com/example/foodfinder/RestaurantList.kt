package com.example.foodfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_restaurant.*


class RestaurantList : Fragment(), RestaurantAdapter.OnRestaurantListener {

    lateinit var restaurantList: ArrayList<RestaurantEntity>;

    var foodDb: FoodDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodDb = FoodDatabase.getInstance(context = this.requireContext())
        restaurantList = foodDb?.restaurantDao()!!.getAllRestaurants() as ArrayList<RestaurantEntity>;
        var favoritesList = foodDb?.restaurantDao()!!.getFavoriteRestaurantForProfile(0)

        restaurantList.forEach(fun(r: RestaurantEntity) {
            val isPresent = favoritesList.filter { it.restaurantId == r.restaurantId }
            if (isPresent.size > 0) r.isFavoriteForActualProfile = true
        })

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
        // Recycle viewer inicializalasa
        var resAdapter: RestaurantAdapter = RestaurantAdapter(restaurantList, this)
        rList.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // beallitjuk a RecycleViewer Adapter osztalyat.
            adapter = resAdapter
        }
    }

    companion object {
        fun newInstance(): RestaurantList = RestaurantList()
    }

    // a felhasznalo kivalasztott egy vendeglot, ezert megjelenitjuk azt egy uj RestaurantDetail fragmensben
    override fun onRestaurantClick(position: Int) {
        // a viewModelbe beallitjuk a kivalasztott fragmenst
        val viewModel: RestaurantViewModel by activityViewModels()
        viewModel.selectRestaurant(restaurantList.get(position))
        setFragment(fragment = RestaurantDetailFragment(restaurantList.get(position)))
    }

    override fun onRestaurantFavorited(position: Int) {
        val viewModel: RestaurantViewModel by activityViewModels()
        var profileRestaurantRef: ProfileRestaurantRef = ProfileRestaurantRef(restaurantList.get(position).restaurantId,
                viewModel.selectedProfile.value!!.profileId)

        if (!restaurantList.get(position).isFavoriteForActualProfile) {
            foodDb?.restaurantDao()!!.insertFavorite(profileRestaurantRef);
        }
        else {
            foodDb?.restaurantDao()!!.deleteFavorite(profileRestaurantRef);
        }
        restaurantList.get(position).isFavoriteForActualProfile = !restaurantList.get(position).isFavoriteForActualProfile
    }

    fun setFragment(fragment: Fragment?) {
        val fm: FragmentManager? = parentFragmentManager
        val fragmentTransaction: FragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_placeHolder, fragment!!)
        fragmentTransaction.commit()
    }


}