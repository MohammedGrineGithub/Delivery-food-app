package com.example.deliveryfoodapp.backend_services.network_images_api

import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface NetworkImagesApiService {

    @Multipart
    @POST("1/upload")
    suspend fun uploadImage(
        @Query("key") apiKey: String,
        @Query("expiration") expiration: Int,
        @Part("image") image: RequestBody
    ): Map<String, Any?>
}