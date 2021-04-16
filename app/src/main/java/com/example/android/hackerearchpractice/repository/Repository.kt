package com.example.android.hackerearchpractice.repository

import android.content.Context
import com.example.android.hackerearchpractice.api.ApiService
import com.example.android.hackerearchpractice.db.AppDatabase
import com.example.android.hackerearchpractice.db.WeatherDao
import com.example.android.hackerearchpractice.util.AppExecutors
import com.example.android.hackerearchpractice.vo.Resource
import com.example.android.hackerearchpractice.vo.ResponseHandler
import com.example.android.hackerearchpractice.vo.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: AppDatabase,
    private val dao: WeatherDao,
    private val service: ApiService,
    private val context: Context
) {
    fun fetchWeatherData(responseHandler: ResponseHandler<Resource<Boolean>>) {
        responseHandler.response(Resource.loading(null))

        service.getWeatherData().enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                appExecutors.mainThread().execute {
                    if (response.isSuccessful) {
                        insertData(response.body())
                        responseHandler.response(Resource.success(true))
                    } else {
                        responseHandler.response(Resource.error(null, false))
                    }
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                appExecutors.mainThread().execute {
                    responseHandler.response(Resource.error(null, false))
                }
            }
        })
    }

    private fun insertData(data: WeatherData?) {
        data?.let {
            appExecutors.diskIO().execute {
                dao.insert(it.data)
            }
        }
    }

    fun getWeatherData() = dao.getAll()
}
