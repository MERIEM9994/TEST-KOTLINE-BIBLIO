package com.example.bookstoreapp.ui.profile
import androidx.compose.ui.Modifier


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookstoreapp.viewmodel.UserViewModel

@Composable
fun ProfileScreen(viewModel: UserViewModel = viewModel()) {
    val user = viewModel.currentUser.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text("Profil", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nom d'utilisateur : ${user?.username}")
        Text("Email : ${user?.email}")
        Text("Adresse : ${user?.address}")
        Text("Rôle : ${user?.role}")
    }
}
