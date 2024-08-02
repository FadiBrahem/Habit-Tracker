package com.example.habittracker.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(onBackClick: () -> Unit) {
    val viewModel: WeatherViewModel = viewModel()
    val weatherState = viewModel.weatherState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                Log.d("WeatherScreen", "Get Weather button clicked")
                viewModel.fetchWeather("Tunisia", "")
            }) {
                Text(text = "Get Weather")
            }
            Spacer(modifier = Modifier.height(16.dp))
            weatherState?.let { weather ->
                // Display the weather information based on your actual data structure
                Text(text = "City: New York") // Hardcoded for now
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Temperature: ${weather.main.temp}°C")
                // Add more weather information as needed
            }
        }
    }
}
