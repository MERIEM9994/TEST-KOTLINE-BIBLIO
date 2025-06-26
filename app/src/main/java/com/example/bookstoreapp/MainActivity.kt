package com.example.bookstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookstoreapp.nav.AppNavigation

import com.example.bookstoreapp.ui.product.screens.ProductViewModel
import com.example.bookstoreapp.ui.theme.BookStoreAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookStoreAppTheme {
                AppContent(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun AppContent(
    viewModel: ProductViewModel,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AppNavigation(viewModel = viewModel)
    }
}