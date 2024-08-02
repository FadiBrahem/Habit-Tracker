package com.example.habittracker.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.CalculatorViewModel
import com.example.habittracker.viewmodel.UserViewModel
import com.example.habittracker.viewmodel.GalleryViewModel
sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object SignUpScreen : Screen("signup_screen")
    object HabitScreen : Screen("habit_screen")
    object IconGridScreen : Screen("icon_grid_screen")
    object CalculatorUI : Screen("calculator_screen")
    object Stopwatch : Screen("stopwatch_screen")
    object Gallery : Screen("gallery_screen")
}



@Composable
fun AppNavigation(userViewModel: UserViewModel, modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    val galleryViewModel: GalleryViewModel = viewModel()
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
            HabitScreen(
                userViewModel = userViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.IconGridScreen.route) {
            IconGridScreen(navController)
        }
        composable(Screen.CalculatorUI.route) {
            CalculatorUI(
                viewModel = CalculatorViewModel(),
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("weather") {
            WeatherScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Stopwatch.route) {
            StopwatchScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Gallery.route) {
            GalleryScreen(
                viewModel = galleryViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

    }
}
