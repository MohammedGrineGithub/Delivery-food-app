package com.example.deliveryfoodapp.backend_services.network_images_api

import com.example.deliveryfoodapp.backend_services.ApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

object NetworkImagesEndpoints {

    private val apiService = ApiClient.imageUrlRetrofit.create(NetworkImagesApiService::class.java)

    suspend fun uploadImageAsBase64(apiKey: String, base64Image: String?): String? {
        if (base64Image != null){
            val requestBody = base64Image.toRequestBody("text/plain".toMediaTypeOrNull())
            val response: Map<String, Any?> = apiService.uploadImage(apiKey, 0, requestBody)
            val success = response["success"] as? Boolean ?: false
            if (success) {
                val data = response["data"] as? Map<String, Any?>
                val image = data?.get("image") as? Map<String, Any?>
                return image?.get("url") as? String
            }
        }
        return null
    }
}