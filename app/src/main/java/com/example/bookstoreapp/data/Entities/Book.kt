package com.example.bookstoreapp.data.Entities

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val type: String,
    val description: String,
    val quantity: Int,
    val image: String,
    val price: Double
)
