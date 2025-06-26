package com.example.bookstoreapp.data.Entities

data class OrderItem(
    val id: Int,
    val quantity: Int
)

data class OrderRequest(
    val items: List<OrderItem>
)
