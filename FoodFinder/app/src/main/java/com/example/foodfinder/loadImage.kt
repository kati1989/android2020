package com.example.foodfinder

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File

// seged metodus kepek betoltesere Glide API segitsegevel
fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions()
        .placeholder(R.drawable.ic_launcher_foreground)  // egy alapertelemezett hely megadasa
        .circleCrop() // kor alaku legyen a megadott kep
        .error(R.mipmap.ic_launcher_round)  // hiba eseten egy alapertelmezett kep jelenjen meg

    Glide.with(this.context)
        .setDefaultRequestOptions(options) // az elozoleg definialt option- opciokat adjuk meg
        .load(uri)  // eleresi utvonala a kepnek (internet)
        .into(this)
}