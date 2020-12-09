package com.example.foodfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter osztaly a Restaurant Entitasok RecyclerViewer ben valo megjelenitesehez,
// ahhoz hogy egy Restaurant kijelolesevel megjelenitsuk a Restaurant reszleteit van egy onRestaurantListener, mely
// figyeli hogy mikor kattintott egy felhasznalo a Restaurantra.
class RestaurantAdapter(private val restaurants: ArrayList<RestaurantEntity>, private val onRestaurantListener: OnRestaurantListener ) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) : RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(inflater, parent, onRestaurantListener)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder:RestaurantViewHolder, position: Int) {
        val restaurant: RestaurantEntity = restaurants[position]
        holder.bind(restaurant)
    }

    class RestaurantViewHolder(inflater: LayoutInflater, parent: ViewGroup, var onRestaurantListener: OnRestaurantListener) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_restaurant, parent, false)),
            View.OnClickListener {
        private val imageView : ImageView? =  itemView.findViewById(R.id.imageView)
        private val title : TextView? = itemView.findViewById(R.id.title)
        private val price : TextView? = itemView.findViewById(R.id.price)
        private  val click : Unit = itemView.setOnClickListener(this)

        fun bind(rest: RestaurantEntity) {
            title?.text = rest.title
            price?.text = "$".repeat(rest.price)
            imageView?.loadImage(rest.image)
        }

        // hogyha az Adapterunkre rakattintott a felhasznalo tovabb delegaljuk ezt az eventet annak
        // aki az OnRestaurantListener eventre feliratkozott (RestaurantList fragmensnek)
        override fun onClick(v: View?) {
            onRestaurantListener.onRestaurantClick(adapterPosition);
        }
    }

    interface OnRestaurantListener{
        fun onRestaurantClick(position: Int)
    }

}