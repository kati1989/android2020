package com.example.foodfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_add_restaurant.*
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_restaurant.*

private const val ARG_PROFILE = "profile"


/**
 * Egy egyszeru Fragment osztaly a Profilok editalasara.
 */
class EditProfile(val notifySaveProfile: NotifySaveProfile) : Fragment() ,  View.OnClickListener{

    private var profile: ProfileEntity? = null;
    var foodDb : FoodDatabase? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodDb = FoodDatabase.getInstance(context = this.requireContext())
        // feltoltjuk a profile valtozonkat az adatbazisbol erkezo profilbol
        // mivel meg nincs login a 0 - adik profilt vesszuk
        var profileList : List<ProfileEntity> = foodDb?.profileDao()!!.getProfile() as ArrayList<ProfileEntity>;
        profile = profileList.get(0)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view : View=  inflater.inflate(R.layout.fragment_edit_profile, container, false)
        return  view
    }

    //miutan letrejott a nezet inicializaljuk az egyes mezoket kezdeti ertekekkel az adatbazisbol
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_name.setText(profile?.name)
        edit_address.setText(profile?.address)
        edit_email.setText(profile?.email)
        edit_phone.setText(profile?.phone)
        edit_pic.setText(profile?.profilePic)
        saveProfile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.saveProfile) {
                // eltaroljuk a viewModelbe a Profilet
                val viewModel: RestaurantViewModel by activityViewModels()
                viewModel.selectProfile(ProfileEntity(profile!!.profileId, edit_name.text.toString(),
                    edit_address.text.toString(),edit_pic.text.toString(), edit_email.text.toString(),
                    edit_phone.text.toString(), ""))
                //kikuldjuk az onSaveProfileClicked eventet, amit az activity kezel
                notifySaveProfile.onSaveProfileClicked();
            }
        }
    }

    interface NotifySaveProfile{
        fun onSaveProfileClicked()
    }

}