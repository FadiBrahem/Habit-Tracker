package com.example.habittracker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.work.*
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.example.habittracker.ui.theme.HabitTrackerTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        scheduleDailyReminder()

        setContent {
            HabitTrackerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HabitTracker()
                }
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Habit Channel"
            val descriptionText = "Channel for habit reminders"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("habit_channel", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun scheduleDailyReminder() {
        val workManager = WorkManager.getInstance(applicationContext)
        val dailyWorkRequest = PeriodicWorkRequestBuilder<ReminderWorker>(24, TimeUnit.HOURS)
            .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "dailyReminder",
            ExistingPeriodicWorkPolicy.REPLACE,
            dailyWorkRequest
        )
    }

    private fun calculateInitialDelay(): Long {
        val currentTime = System.currentTimeMillis()
        val targetTime = currentTime + TimeUnit.HOURS.toMillis(24)
        return targetTime - currentTime
    }
}
