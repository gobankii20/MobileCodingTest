package com.vitavat.mobilecodingtest.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.setImageView(urlImage: String, isImageFitCenter: Boolean? = false) {
    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    if (isImageFitCenter == true) {
        Glide
            .with(this.context)
            .load(urlImage)
            .apply(requestOptions)
            .centerCrop()
            .into(this)
    } else {
        Glide
            .with(this.context)
            .load(urlImage)
            .apply(requestOptions)
            .fitCenter()
            .into(this)
    }
}
