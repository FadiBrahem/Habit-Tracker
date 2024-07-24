package com.example.habittracker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Habit(
    val name: String,
    var isCompleted: Boolean = false
)

@Composable
fun HabitTracker() {
    var habits by remember { mutableStateOf(listOf<Habit>()) }
    var newHabitName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Habit Tracker", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        BasicTextField(
            value = newHabitName,
            onValueChange = { newHabitName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, MaterialTheme.colors.primary)
                ) {
                    if (newHabitName.isEmpty()) {
                        Text("Enter new habit", color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f))
                    }
                    innerTextField()
                }
            }
        )

        Button(
            onClick = {
                if (newHabitName.isNotBlank()) {
                    habits = habits + Habit(newHabitName)
                    newHabitName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Habit")
        }

        Spacer(modifier = Modifier.height(16.dp))

        habits.forEach { habit ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = habit.isCompleted,
                    onCheckedChange = { isChecked ->
                        habits = habits.map {
                            if (it.name == habit.name) it.copy(isCompleted = isChecked) else it
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(habit.name, fontSize = 20.sp)
            }
        }
    }
}