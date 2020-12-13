package com.example.movieapp.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp.R
import com.example.movieapp.util.keys.AppObject

@BindingAdapter("movieImage")
fun setCharacterImage(imageView: AppCompatImageView, url: String?) {
    Glide.with(imageView.context)
        .load(AppObject.imageBaseUrl+url)
        .timeout(15000)
        .apply(
            RequestOptions()
                .error(R.mipmap.ic_launcher)
                .fitCenter()
        )
        .fitCenter()
        .into(imageView)
}

