package com.example.foodfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantAdapter(var restaurants: ArrayList<RestaurantEntity>) :
    RecyclerView.Adapter<RestaurantAdapter.UserViewHolder>() {

    fun updateRestaurants(newRestaurants: ArrayList<RestaurantEntity>) {
        restaurants.clear()
         restaurants.addAll(newRestaurants)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
    )

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.imageView
        private val userName = view.name
        private val userEmail = view.email

        fun bind(rest: RestaurantEntity) {
            userName.text = rest.title
            imageView.loadImage(rest.image)
        }
    }
}