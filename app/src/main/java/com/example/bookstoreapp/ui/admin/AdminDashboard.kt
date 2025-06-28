package com.example.bookstoreapp.ui.admin
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*

@Composable
fun AdminDashboard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text("Tableau de bord Admin", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Ici, vous pourrez ajouter des livres, voir les utilisateurs, modifier les stocks, etc.")
    }
}
