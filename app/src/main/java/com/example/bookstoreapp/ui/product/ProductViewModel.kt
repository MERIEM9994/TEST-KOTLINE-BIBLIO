package com.example.bookstoreapp.ui.product.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoreapp.data.Entities.Book
import com.example.bookstoreapp.data.Repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductViewState {
    object Loading : ProductViewState()
    data class Success(val books: List<Book>) : ProductViewState()
    data class Error(val message: String) : ProductViewState()
}

class ProductViewModel : ViewModel() {

    private val repository = BookRepository()

    private val _state = MutableStateFlow<ProductViewState>(ProductViewState.Loading)
    val state: StateFlow<ProductViewState> = _state

    init {
        fetchBooks()
    }

    //  Rendre cette fonction publique pour pouvoir la rappeler après un achat
    fun fetchBooks() {
        viewModelScope.launch {
            try {
                val books = repository.getBooks()
                _state.value = ProductViewState.Success(books)
            } catch (e: Exception) {
                _state.value = ProductViewState.Error("Erreur lors du chargement : ${e.message}")
            }
        }
    }

    fun getBookById(id: Int): Book? {
        val currentState = _state.value
        return if (currentState is ProductViewState.Success) {
            currentState.books.find { it.id == id }
        } else {
            null
        }
    }
}


