package com.sawitpro.weightbridge.util

import android.view.View

fun Int?.orZero() = this ?: 0

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}