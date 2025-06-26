package com.example.bookstoreapp.ui.product.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bookstoreapp.data.Entities.Book
import com.example.bookstoreapp.nav.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsProductScreen(
    book: Book,
    navController: NavController,              // ✅ Ajouté
    onBackClick: () -> Unit,
    onAddToCart: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Détails du livre") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Image
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                AsyncImage(
                    model = book.image,
                    contentDescription = "Couverture de ${book.title}",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
                )
            }

            // Titre
            Text(
                text = book.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            // Auteur
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Auteur :", fontWeight = FontWeight.Bold)
                Text(book.author)
            }

            // Genre
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Genre :", fontWeight = FontWeight.Bold)
                Text(book.type.replace("_", " "))
            }

            // Prix
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Prix :", fontWeight = FontWeight.Bold)
                Text("${book.price} MAD", color = MaterialTheme.colorScheme.secondary)
            }

            // Disponibilité
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Disponibilité :", fontWeight = FontWeight.Bold)
                Text(
                    text = if (book.quantity > 0) "${book.quantity} exemplaires" else "Rupture de stock",
                    color = if (book.quantity > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ✅ Bouton Ajouter au panier avec navigation
            Button(
                onClick = {
                    onAddToCart()
                    navController.navigate(Routes.CART)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ajouter au panier")
            }

            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text("Retour à la liste")
            }
        }
    }
}


