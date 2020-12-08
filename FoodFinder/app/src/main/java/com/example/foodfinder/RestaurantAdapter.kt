package com.example.foodfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_restaurant.view.*

class RestaurantAdapter(private val restaurants: ArrayList<RestaurantEntity>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    fun updateRestaurants(newRestaurants: ArrayList<RestaurantEntity>) {
        restaurants.clear()
        restaurants.addAll(newRestaurants)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) : RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(inflater, parent)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder:RestaurantViewHolder, position: Int) {
        val restaurant: RestaurantEntity = restaurants[position]
        holder.bind(restaurant)
    }

    class RestaurantViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_restaurant, parent, false)) {

        private val imageView : ImageView? =  itemView.findViewById(R.id.imageView)
        private val title : TextView? = itemView.findViewById(R.id.title)
        private val price : TextView? = itemView.findViewById(R.id.price)

        fun bind(rest: RestaurantEntity) {
            title?.text = rest.title
            price?.text = rest.price.toString()
            imageView?.loadImage(rest.image)
        }





    }



}