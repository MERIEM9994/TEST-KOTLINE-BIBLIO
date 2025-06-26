package com.example.bookstoreapp.data.Api

import com.example.bookstoreapp.data.Entities.Book
import com.example.bookstoreapp.data.Entities.OrderRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookApiService {

    @GET("/books")
    suspend fun getBooks(): List<Book>

    @POST("/order")
    suspend fun postOrder(@Body orderRequest: OrderRequest): Response<Unit>
}

