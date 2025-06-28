package com.example.bookstoreapp.ui.profile
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookstoreapp.viewmodel.UserViewModel
import com.example.bookstoreapp.R

@Composable
fun ProfileScreen(viewModel: UserViewModel = viewModel()) {
    val user = viewModel.currentUser.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Image de fond
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().alpha(0.85f)
        )

        // Carte semi-transparente contenant les infos du profil
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .align(Alignment.Center),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xCC000000)) // noir transparent
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("Profil", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Nom d'utilisateur : ${user?.username ?: "N/A"}", color = Color.White)

                Text("Adresse : ${user?.address ?: "N/A"}", color = Color.White)
                Text("Rôle : ${user?.role ?: "N/A"}", color = Color.White)
            }
        }
    }
}

