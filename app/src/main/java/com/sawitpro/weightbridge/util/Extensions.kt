package com.sawitpro.weightbridge.util

import android.text.Editable
import android.view.View

fun Int?.orZero() = this ?: 0

fun Editable?.toInt(): Int {
    val str = this.toString()
    val int: Int = if (str.isEmpty() || str == "null") {
        0
    } else str.toInt()

    return int
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}