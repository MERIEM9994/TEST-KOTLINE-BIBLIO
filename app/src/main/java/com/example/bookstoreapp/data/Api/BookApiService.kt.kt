package com.example.bookstoreapp.data.Api
import com.example.bookstoreapp.data.Entities.Book
import retrofit2.http.GET

interface BookApiService {
    @GET("/books")
    suspend fun getBooks(): List<Book>
}
