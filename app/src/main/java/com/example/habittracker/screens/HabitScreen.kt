package com.example.habittracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habittracker.viewmodel.UserViewModel

@Composable
fun HabitScreen(userViewModel: UserViewModel) {
    val habits = remember { mutableStateListOf("") }
    var newHabit by remember { mutableStateOf("") }
    var daysChecked = remember { mutableStateListOf(false, false, false, false, false, false, false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = newHabit,
            onValueChange = { newHabit = it },
            label = { Text("New Habit") },
            modifier = Modifier.fillMaxWidth().height(60.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { if (newHabit.isNotEmpty()) habits.add(newHabit) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Habit")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(habits) { habit ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(habit, modifier = Modifier.weight(1f))
                    Checkbox(
                        checked = daysChecked.all { it },
                        onCheckedChange = { isChecked ->
                            if (isChecked) daysChecked.replaceAll { true }
                        }
                    )
                }
            }
        }
    }
}