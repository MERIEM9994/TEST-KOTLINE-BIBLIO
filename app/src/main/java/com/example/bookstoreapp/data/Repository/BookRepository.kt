package com.example.bookstoreapp.data.Repository

import com.example.bookstoreapp.data.Entities.Book
import com.example.bookstoreapp.data.Api.RetrofitInstance

class BookRepository {

    // Récupérer la liste des livres via l'API Retrofit
    suspend fun getBooks(): List<Book> {
        return RetrofitInstance.api.getBooks()
    }
}


