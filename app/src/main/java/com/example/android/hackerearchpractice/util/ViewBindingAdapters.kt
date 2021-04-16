package com.example.android.hackerearchpractice.util

import android.annotation.SuppressLint
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) VISIBLE else GONE
}

@BindingAdapter("temperature")
fun setTemperature(textView: TextView, temperature: Int) {
    textView.text = "$temperature \u2103"
}


const val DATE_FORMAT2 = "MMM dd yyyy"

@BindingAdapter("date")
fun setDate(textView: TextView, time: Long) {
    textView.text = formattedTime(time)
}

@SuppressLint("SimpleDateFormat")
fun formattedTime(timeStamp: Long): String {
    val formatter = SimpleDateFormat(DATE_FORMAT2)
    return formatter.format(Date(timeStamp))
}

@BindingAdapter("rain")
fun setRain(textView: TextView, rain: Int) {
    textView.text = "$rain%"
}

@BindingAdapter("wind")
fun setWind(textView: TextView, speed: Int) {
    textView.text = "$speed km/h"
}
