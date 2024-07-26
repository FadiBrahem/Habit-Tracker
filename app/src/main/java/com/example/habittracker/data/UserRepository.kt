package com.example.habittracker.data

class UserRepository {
    private val users = mutableListOf<Pair<String, String>>()

    suspend fun login(email: String, password: String): Boolean {
        return users.any { it.first == email && it.second == password }
    }

    suspend fun signUp(email: String, password: String) {
        users.add(email to password)
    }
}
