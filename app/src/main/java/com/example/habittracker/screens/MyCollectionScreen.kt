package com.example.habittracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.R

// Define a data class to represent each icon item
data class IconItem(val imageRes: Int, val text: String, val route: String)


val iconItems = listOf(
    IconItem(R.drawable.calculator, "Calculator", "calculatorRoute"),
    IconItem(R.drawable.todolist, "ToDoList", Screen.HabitScreen.route),
    IconItem(R.drawable.weather, "Weather", "Weather"),
    IconItem(R.drawable.stopwatch, "Stopwatch", "StopwatchRoute"),
    IconItem(R.drawable.notes, "Notes", "NotesRoute")
)

@Composable
fun IconGridScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        iconItems.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                rowItems.forEach { item ->
                    IconItemView(item = item, navController = navController)
                }

                repeat(3 - rowItems.size) {
                    Spacer(modifier = Modifier.width(64.dp))
                }
            }
        }
    }
}

@Composable
fun IconItemView(item: IconItem, navController: NavController) {
    Column(
        modifier = Modifier
            .width(64.dp)
            .clickable { navController.navigate(item.route) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.text,
            modifier = Modifier
                .size(64.dp)
                .padding(bottom = 8.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = item.text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// Add composable previews if needed for UI design
@Preview(showBackground = true)
@Composable
fun IconGridScreenPreview() {
    IconGridScreen(navController = rememberNavController())
}
