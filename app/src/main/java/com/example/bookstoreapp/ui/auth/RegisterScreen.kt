package com.example.bookstoreapp.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookstoreapp.viewmodel.UserViewModel

@Composable
fun RegisterScreen(
    viewModel: UserViewModel = viewModel(),
    onRegisterSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState() // <-- On observe les erreurs ou succès

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Inscription", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Nom d'utilisateur") })
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Adresse") })
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.register(
                username = username,
                password = password,
                email = email,
                address = address,
                onSuccess = onRegisterSuccess
            )
        }) {
            Text("Créer un compte")
        }

        if (loginState.isError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = loginState.message,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

