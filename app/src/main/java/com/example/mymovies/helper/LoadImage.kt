package com.example.mymovies.helper

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.mymovies.R

fun ImageView.loadImage(
    context: Context,
    url: String,
    @DrawableRes placeholder: Int = R.drawable.img_no_images
) {

    // add loading image
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 50f
    circularProgressDrawable.setColorSchemeColors(
        ContextCompat.getColor(context, R.color.purple_200)
    )
    circularProgressDrawable.start()

    Glide.with(this)
        .load(url)
        .placeholder(circularProgressDrawable)
        .error(R.drawable.img_no_images)
        .into(this)

}