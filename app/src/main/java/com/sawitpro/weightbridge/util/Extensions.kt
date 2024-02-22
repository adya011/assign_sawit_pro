package com.sawitpro.weightbridge.util

import android.text.Editable
import android.view.View
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Editable?.toDouble(): Double {
    val str = this.toString()
    val dbl: Double = if (str.isEmpty() || str == "null") {
        0.0
    } else str.toDouble()

    return dbl
}

fun Double?.formatDouble() : String {
    return String.format("%.1f", this)
}

fun Double?.format() : Double {
    return this.formatDouble().toDouble()
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return sdf.format(Date())
}