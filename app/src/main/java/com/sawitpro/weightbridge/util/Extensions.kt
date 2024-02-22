package com.sawitpro.weightbridge.util

import android.text.Editable
import android.util.Log
import android.view.View
import java.text.ParseException
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

fun String?.formatDate(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val date: Date?
    var finalString = "-"

    this?.let {
        try {
            date = formatter.parse(it)
            date?.let { dateData ->
                val newFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)
                finalString = newFormat.format(dateData)
            }
        } catch (e: ParseException) {
            Log.e("error","ParseException convertToDdMmmYyyy : $e")
        }
    }
    return finalString
}