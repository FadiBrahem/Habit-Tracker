package com.example.habittracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.network.WeatherApiService
import com.example.habittracker.network.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApiService = WeatherApiService.create()

    private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
    val weatherState: StateFlow<WeatherResponse?> = _weatherState

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = weatherApiService.getWeather(city, apiKey)
                _weatherState.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
