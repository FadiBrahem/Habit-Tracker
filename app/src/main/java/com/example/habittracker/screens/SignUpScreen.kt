package com.example.habittracker.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habittracker.viewmodel.UserViewModel
import com.example.habittracker.utils.isValidEmail

@Composable
fun SignUpScreen(navController: NavController, userViewModel: UserViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = email.isNotEmpty() && !isValidEmail(email)
        )
        if (email.isNotEmpty() && !isValidEmail(email)) {
            Text(
                text = "Invalid email format",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = password.isNotEmpty() && password.length < 6
        )
        if (password.isNotEmpty() && password.length < 6) {
            Text(
                text = "Password must be at least 6 characters",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // First Name Field
        OutlinedTextField(
            value = firstname,
            onValueChange = { firstname = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = firstname.isNotEmpty() && firstname.isBlank()
        )
        if (firstname.isNotEmpty() && firstname.isBlank()) {
            Text(
                text = "First name cannot be empty",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Last Name Field
        OutlinedTextField(
            value = lastname,
            onValueChange = { lastname = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = lastname.isNotEmpty() && lastname.isBlank()
        )
        if (lastname.isNotEmpty() && lastname.isBlank()) {
            Text(
                text = "Last name cannot be empty",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button
        Button(
            onClick = {
                when {
                    !isValidEmail(email) -> {
                        dialogMessage = "Invalid email format"
                        showDialog = true
                    }
                    password.length < 6 -> {
                        dialogMessage = "Password must be at least 6 characters"
                        showDialog = true
                    }
                    firstname.isBlank() -> {
                        dialogMessage = "First name cannot be empty"
                        showDialog = true
                    }
                    lastname.isBlank() -> {
                        dialogMessage = "Last name cannot be empty"
                        showDialog = true
                    }
                    else -> {
                        userViewModel.signUp(email, password)
                        dialogMessage = "Sign Up Successful!"
                        showDialog = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Navigate to Login Screen
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")
        }

        // Dialog for showing messages
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Notification") },
                text = { Text(dialogMessage) },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        if (dialogMessage == "Sign Up Successful!") {
                            navController.popBackStack()
                        }
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
