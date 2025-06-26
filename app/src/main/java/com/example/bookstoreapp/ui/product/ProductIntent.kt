package com.example.bookstoreapp.ui.product

sealed class ProductIntent {
    object LoadBooks : ProductIntent()
    data class ShowBookDetails(val bookId: Int) : ProductIntent()
    object NavigateBack : ProductIntent()
}
