package com.example.habittracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habittracker.viewmodel.UserViewModel

@Composable
fun HabitScreen(userViewModel: UserViewModel, onBackClick: () -> Unit) {
    val habits = remember { mutableStateListOf<String>() }
    var newHabit by remember { mutableStateOf("") }
    val daysChecked = remember { mutableStateMapOf<Int, List<Boolean>>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Habit Tracker") },
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = newHabit,
                onValueChange = { newHabit = it },
                label = { Text("New Habit") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (newHabit.isNotEmpty()) {
                        habits.add(newHabit)
                        daysChecked[habits.lastIndex] = List(7) { false }
                        newHabit = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Habit")
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                itemsIndexed(habits) { index, habit ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(habit, modifier = Modifier.weight(1f))
                        daysChecked[index]?.forEachIndexed { dayIndex, isChecked ->
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { newValue ->
                                    daysChecked[index] = daysChecked[index]!!.toMutableList().also {
                                        it[dayIndex] = newValue
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
