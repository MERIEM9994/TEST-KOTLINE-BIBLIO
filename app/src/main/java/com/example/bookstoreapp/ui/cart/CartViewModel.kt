package com.example.bookstoreapp.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoreapp.data.Api.RetrofitInstance
import com.example.bookstoreapp.data.Entities.Book
import com.example.bookstoreapp.data.Entities.OrderItem
import com.example.bookstoreapp.data.Entities.OrderRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CartItem(
    val book: Book,
    val quantity: Int = 1
)

sealed class CartUiState {
    object Empty : CartUiState()
    data class Success(val items: List<CartItem>, val total: Double) : CartUiState()
}

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    private val _uiState = MutableStateFlow<CartUiState>(CartUiState.Empty)
    val uiState: StateFlow<CartUiState> = _uiState

    fun addToCart(book: Book) {
        val existing = _cartItems.value.find { it.book.id == book.id }
        if (existing == null) {
            _cartItems.value = _cartItems.value + CartItem(book)
        }
        updateUiState()
    }

    fun clearCart() {
        _cartItems.value = emptyList()
        _uiState.value = CartUiState.Empty
    }

    fun confirmPurchase() {
        viewModelScope.launch {
            try {
                if (_cartItems.value.isEmpty()) return@launch

                val orderItems = _cartItems.value.map {
                    OrderItem(id = it.book.id, quantity = it.quantity)
                }

                val request = OrderRequest(items = orderItems)
                val response = RetrofitInstance.api.postOrder(request)

                if (response.isSuccessful) {
                    // Mettre à jour localement le stock
                    _cartItems.value = _cartItems.value.map {
                        val updatedBook = it.book.copy(quantity = it.book.quantity - it.quantity)
                        CartItem(updatedBook, it.quantity)
                    }
                    clearCart()  // Vider le panier après succès
                    println("✅ Commande envoyée avec succès")
                } else {
                    println("❌ Échec de la commande : ${response.code()}")
                }

            } catch (e: Exception) {
                println("⚠️ Erreur réseau : ${e.localizedMessage}")
            }
        }
    }

    private fun updateUiState() {
        if (_cartItems.value.isEmpty()) {
            _uiState.value = CartUiState.Empty
        } else {
            val total = _cartItems.value.sumOf { it.book.price * it.quantity }
            _uiState.value = CartUiState.Success(_cartItems.value, total)
        }
    }
}

