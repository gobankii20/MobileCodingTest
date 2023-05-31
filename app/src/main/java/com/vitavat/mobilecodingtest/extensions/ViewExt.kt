package com.vitavat.mobilecodingtest.extensions

import android.view.View


fun View.visibilityViewIconClose(keyword: String) {
    if (keyword.isNotEmpty()) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}