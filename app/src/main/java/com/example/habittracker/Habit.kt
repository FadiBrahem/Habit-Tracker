package com.example.habittracker

data class Habit(
    val name: String,
    var isCompleted: Boolean = false,
    var streak: Int = 0,
    val daysCompleted: MutableList<Boolean> = MutableList(7) { false } // Tracking completion for each day of the week
)
