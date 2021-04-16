package com.example.android.hackerearchpractice.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.hackerearchpractice.repository.Repository
import com.example.android.hackerearchpractice.result.Event
import com.example.android.hackerearchpractice.vo.Resource
import com.example.android.hackerearchpractice.vo.ResponseHandler
import com.example.android.hackerearchpractice.vo.Status
import com.example.android.hackerearchpractice.vo.Weather
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application, repository: Repository) :
    AndroidViewModel(application) {

    val weatherList: LiveData<List<Weather>> = repository.getWeatherData()

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() {
            return _loading
        }

    private val _showError: MutableLiveData<Event<String>> = MutableLiveData()
    val showError: LiveData<Event<String>>
        get() {
            return _showError
        }

    init {
        repository.fetchWeatherData(object : ResponseHandler<Resource<Boolean>> {
            override fun response(response: Resource<Boolean>) {
                _loading.value = response.status == Status.LOADING
                if (response.status == Status.ERROR) {
                    showError()
                }
            }
        })
    }

    private fun showError() {
        _showError.value = Event("Something wrong happened")
    }

}