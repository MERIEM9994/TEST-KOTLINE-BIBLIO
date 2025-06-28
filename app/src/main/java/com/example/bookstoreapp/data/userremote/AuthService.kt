package com.example.bookstoreapp.data.userremote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthService {
    private const val BASE_URL = "http://192.168.1.32:3000/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: UserApi = retrofit.create(UserApi::class.java)
}

