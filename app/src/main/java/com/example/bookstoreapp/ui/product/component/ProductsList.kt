package com.example.bookstoreapp.ui.product.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookstoreapp.data.Entities.Book

@Composable
fun ProductsList(
    books: List<Book>,
    onBookClick: (Int) -> Unit  // Ici on attend un Int, pas String
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(books) { book ->
            ProductsItemComponent(
                book = book,
                onDetailClick = { onBookClick(book.id) }  // book.id est Int
            )
        }
    }
}
