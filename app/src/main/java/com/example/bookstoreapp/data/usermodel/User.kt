
package com.example.bookstoreapp.data.usermodel

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val address: String,
    val role: String,
    val preferences: List<String> = emptyList()
)
