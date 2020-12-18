package com.example.foodfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter osztaly a Restaurant Entitasok RecyclerViewer ben valo megjelenitesehez,
// ahhoz hogy egy Restaurant kijelolesevel megjelenitsuk a Restaurant reszleteit van egy onRestaurantListener, mely
// figyeli hogy mikor kattintott egy felhasznalo a Restaurantra.
class RestaurantAdapter(private val restaurants: ArrayList<RestaurantEntity>, private val onRestaurantListener: OnRestaurantListener) :
        RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(inflater, parent, onRestaurantListener)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant: RestaurantEntity = restaurants[position]
        holder.bind(restaurant)
    }

    //adatok osszekotese a ui -on levo nezettel
    class RestaurantViewHolder(inflater: LayoutInflater, parent: ViewGroup, var onRestaurantListener: OnRestaurantListener) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_restaurant, parent, false)),
            View.OnClickListener {
        private val imageView: ImageView? = itemView.findViewById(R.id.imageView)
        private val title: TextView? = itemView.findViewById(R.id.title)
        private val price: TextView? = itemView.findViewById(R.id.price)
        private val favorite: ImageButton = itemView.findViewById(R.id.favoriteButton)
        private val click: Unit = itemView.setOnClickListener(this)

        fun bind(rest: RestaurantEntity) {
            title?.text = rest.title
            price?.text = "$".repeat(rest.price)
            if (rest.image.isNotBlank())
                imageView?.loadImage(rest.image)
            if (rest.isFavoriteForActualProfile)
                favorite.setImageResource(android.R.drawable.star_big_on)
            else
                favorite.setImageResource(android.R.drawable.star_big_off)
                favorite.setOnClickListener(View.OnClickListener {
                        if (!rest.isFavoriteForActualProfile)
                            favorite.setImageResource(android.R.drawable.star_big_on)
                        else
                            favorite.setImageResource(android.R.drawable.star_big_off)
                    onRestaurantListener.onRestaurantFavorited(adapterPosition);
                })
        }
        // hogyha az Adapterunkre rakattintott a felhasznalo tovabb delegaljuk ezt az eventet annak
        // aki az OnRestaurantListener eventre feliratkozott (RestaurantList fragmensnek)
        // adapterPosition tarolja h melyik Restaurant-ra klikkelt a felhasznalo.
        override fun onClick(v: View?) {
            onRestaurantListener.onRestaurantClick(adapterPosition);
        }
    }

    interface OnRestaurantListener {
        fun onRestaurantClick(position: Int)
        fun onRestaurantFavorited(position:Int)
    }

}