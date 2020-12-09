package com.example.foodfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_restaurant.*

private const val ARG_PROFILE = "profile"


/**
 * A simple [Fragment] subclass.
 * Use the [EditProfile.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var profile: ProfileEntity? = null;
    var foodDb : FoodDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodDb = FoodDatabase.getInstance(context = this.requireContext())
        var profileList : List<ProfileEntity> = foodDb?.profileDao()!!.getProfile() as ArrayList<ProfileEntity>;
        profile = profileList.get(0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view : View=  inflater.inflate(R.layout.fragment_edit_profile, container, false)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_name.setText(profile?.name)
        edit_address.setText(profile?.address)
        edit_email.setText(profile?.email)
        edit_phone.setText(profile?.phone)
    }

    companion object {
        fun newInstance(): EditProfile = EditProfile()
    }
}