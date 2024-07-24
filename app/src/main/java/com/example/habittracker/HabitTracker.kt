package com.example.habittracker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import androidx.compose.ui.viewinterop.AndroidView
import java.util.Calendar

@Composable
fun HabitTracker() {
    var habits by remember { mutableStateOf(listOf<Habit>()) }
    var newHabitName by remember { mutableStateOf("") }
    var daysCompleted by remember { mutableStateOf(MutableList(7) { false }) }

    fun updateDayCompletion() {
        // Update day completion status based on habits
        daysCompleted = MutableList(7) { dayIndex ->
            habits.all { it.daysCompleted[dayIndex] }
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Habit Tracker", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        BasicTextField(
            value = newHabitName,
            onValueChange = { newHabitName = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp),
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
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

        // Display Days of the Week
        DaysOfWeek(daysCompleted)

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
                            if (it.name == habit.name) {
                                val updatedDaysCompleted = it.daysCompleted.toMutableList()
                                val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
                                updatedDaysCompleted[today] = isChecked
                                if (isChecked) {
                                    it.copy(
                                        isCompleted = isChecked,
                                        streak = it.streak + 1,
                                        daysCompleted = updatedDaysCompleted
                                    )
                                } else {
                                    it.copy(
                                        isCompleted = isChecked,
                                        streak = 0,
                                        daysCompleted = updatedDaysCompleted
                                    )
                                }
                            } else it
                        }
                        updateDayCompletion()
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(habit.name, fontSize = 20.sp)
                    Text("Streak: ${habit.streak}", fontSize = 16.sp)
                }
            }
        }

        HabitProgressChart(habits)
    }
}

@Composable
fun DaysOfWeek(daysCompleted: List<Boolean>) {
    val days = listOf("1", "2", "3", "4", "5", "6", "7")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        days.forEachIndexed { index, day ->
            Text(
                text = day,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(4.dp)
                    .border(1.dp, if (daysCompleted[index]) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface)
                    .padding(8.dp)
                    .background(if (daysCompleted[index]) MaterialTheme.colors.primary.copy(alpha = 0.2f) else MaterialTheme.colors.background)
            )
        }
    }
}

@Composable
fun HabitProgressChart(habits: List<Habit>) {
    val entries = habits.mapIndexed { index, habit ->
        BarEntry(index.toFloat(), habit.streak.toFloat())
    }
    val dataSet = BarDataSet(entries, "Habits")
    val barData = BarData(dataSet)

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                data = barData
                description.isEnabled = false
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                axisLeft.axisMinimum = 0f
                axisRight.isEnabled = false
                invalidate()
            }
        },
        modifier = Modifier.fillMaxWidth().height(200.dp)
    )
}