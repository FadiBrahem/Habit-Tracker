package com.example.habittracker.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.CalculatorViewModel
import com.example.habittracker.viewmodel.UserViewModel

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object HabitScreen : Screen("habit_screen")
    object IconGridScreen : Screen("icon_grid_screen")
    object CalculatorUI : Screen ("CalculatorUI")
}

@Composable
fun AppNavigation(userViewModel: UserViewModel, modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
        modifier = modifier
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController, userViewModel)
        }
        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController, userViewModel)
        }
        composable(Screen.HabitScreen.route) {
            HabitScreen(userViewModel)
        }
        composable(Screen.IconGridScreen.route) {
            IconGridScreen(navController)
        }
        composable(Screen.CalculatorUI.route) {
            CalculatorUI(CalculatorViewModel())
        }
        composable("weather") { WeatherScreen() }
    }
}
