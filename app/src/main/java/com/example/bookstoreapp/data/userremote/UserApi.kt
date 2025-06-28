package com.example.bookstoreapp.data.userremote

import com.example.bookstoreapp.data.usermodel.User
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(
    val message: String,
    val userId: Long,
    val username: String,
    val role: String,
    val address: String
)

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String,
    val address: String,
    val role: String = "client"
)

data class RegisterResponse(
    val message: String,
    val userId: Long
)

interface UserApi {
    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("api/users/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}







