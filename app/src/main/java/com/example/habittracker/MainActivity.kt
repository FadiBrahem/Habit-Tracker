package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker.data.UserRepository

import com.example.habittracker.screens.AppNavigation
import com.example.habittracker.ui.theme.HabitTrackerTheme
import com.example.habittracker.viewmodel.UserViewModel
import com.example.habittracker.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepository = UserRepository()
        val userViewModelFactory = UserViewModelFactory(userRepository)
        val userViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        setContent {
            HabitTrackerTheme {
                AppNavigation(userViewModel)
            }
        }
    }
}
