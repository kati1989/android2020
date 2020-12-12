package com.example.foodfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_add_restaurant.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRestaurant.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRestaurant(val notifySaveRestaurant: NotifySaveRestaurant) : Fragment(),  View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        persistRestaurant.setOnClickListener(this)
        val viewModel: RestaurantViewModel by activityViewModels()

        if (viewModel.selectedItem.value != null) {
            edit_address_restaurant.setText(viewModel.selectedItem.value?.adress)
            edit_title_restaurant.setText(viewModel.selectedItem.value?.title)
            edit_picture_restaurant.setText(viewModel.selectedItem.value?.image)

            if (viewModel.selectedItem.value?.price ==1){
                radioButton.isChecked = true
            }else if(viewModel.selectedItem.value?.price ==2){
                radioButton2.isChecked = true
            } else if (viewModel.selectedItem.value?.price ==3){
                radioButton3.isChecked = true
            } else if (viewModel.selectedItem.value?.price ==4){
                radioButton4.isChecked = true
            }
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.persistRestaurant) {
                var price : Int = 1
                if (radioButton.isChecked)
                    price =1
                if (radioButton2.isChecked)
                    price =2
                if (radioButton3.isChecked)
                    price =3
                if (radioButton4.isChecked)
                    price =4

                val viewModel: RestaurantViewModel by activityViewModels()
                if (viewModel.selectedItem.value == null) {
                    var  restaurant : RestaurantEntity = RestaurantEntity(edit_picture_restaurant.text.toString(),
                        edit_address_restaurant.text.toString(),
                        price,edit_title_restaurant.text.toString(),-1);
                    viewModel.selectItem(restaurant)

                } else {
                    var  restaurant : RestaurantEntity = RestaurantEntity(edit_picture_restaurant.text.toString(),
                        edit_address_restaurant.text.toString(),
                        price,edit_title_restaurant.text.toString(), viewModel.selectedItem.value!!.restaurantId);
                    viewModel.selectItem(restaurant)
                }
            }
            notifySaveRestaurant.onSaveClicked();
            }
    }

    interface NotifySaveRestaurant{
        fun onSaveClicked()
    }

}