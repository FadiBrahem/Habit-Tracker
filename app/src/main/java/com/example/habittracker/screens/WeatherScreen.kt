package com.example.habittracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen() {
    val viewModel: WeatherViewModel = viewModel()
    val weatherState = viewModel.weatherState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.fetchWeather("New York", "your_api_key_here") }) {
            Text(text = "Get Weather")
        }
        Spacer(modifier = Modifier.height(16.dp))
        weatherState?.let {
            Text(text = "Temperature: ${it.main.temp}°C")
        }
    }
}
