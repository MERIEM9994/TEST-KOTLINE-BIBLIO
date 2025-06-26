package com.example.bookstoreapp.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel = viewModel(),
    onValidatePayment: () -> Unit
) {
    val uiState by cartViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("🛒 Mon Panier") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            when (uiState) {
                is CartUiState.Empty -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Votre panier est vide.")
                    }
                }

                is CartUiState.Success -> {
                    val items = (uiState as CartUiState.Success).items
                    val total = (uiState as CartUiState.Success).total

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(items) { item ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(text = item.book.title, style = MaterialTheme.typography.titleMedium)
                                    Text(text = "Prix : ${item.book.price} MAD")
                                    Text(text = "Quantité : ${item.quantity}")
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("💰 Montant total : $total MAD", style = MaterialTheme.typography.titleLarge)

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(onClick = { cartViewModel.clearCart() }) {
                            Text("Vider le panier")
                        }

                        Button(onClick = {
                            cartViewModel.confirmPurchase()
                            onValidatePayment()
                        }) {
                            Text("Commander")
                        }
                    }
                }
            }
        }
    }
}
