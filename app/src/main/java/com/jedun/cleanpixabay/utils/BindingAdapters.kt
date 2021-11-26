package com.jedun.cleanpixabay.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.jedun.cleanpixabay.R


/**
 * Binding adapter to bind image resource to imageview
 */
@BindingAdapter("imageResource")
fun setImageViewResource(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView)
        .load(imageUrl)
        .placeholder(R.drawable.loading_image)
        .error(R.drawable.placeholder)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}
