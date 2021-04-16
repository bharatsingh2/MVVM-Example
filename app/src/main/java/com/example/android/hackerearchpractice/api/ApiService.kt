package com.example.android.hackerearchpractice.api

import com.example.android.hackerearchpractice.vo.WeatherData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("5d3a99ed2f0000bac16ec13a")
    fun getWeatherData(): Call<WeatherData>
}