package com.example.android.hackerearchpractice.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    val rain: Int,
    val temp: Int,
    @PrimaryKey val time: Int,
    val wind: Int
)