package com.example.deliveryfoodapp.models


data class User(
    val fullName: String,
    val email: String,
    val phone: String,
    val locationId: Int,
    val password: String,
    val photoId: Int
) {

    
    fun toMap(): Map<String, Any> = mapOf(
        "fullName" to fullName,
        "email" to email,
        "phone" to phone,
        "locationId" to locationId,
        "password" to password,
        "photoId" to photoId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = User(
            fullName = map["fullName"] as String,
            email = map["email"] as String,
            phone = map["phone"] as String,
            locationId = map["locationId"] as Int,
            password = map["password"] as String,
            photoId = map["photoId"] as Int
        )
    }
}
