package com.example.deliveryfoodapp.backend_services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ENV.NGROK_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}