package com.example.deliveryfoodapp.models


@Suppress("UNCHECKED_CAST")
data class User(
    var id : Int,
    var fullName: String,
    var email: String,
    var phone: String,
    var location: Location,
    var photo: AppImage
) {

    
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "fullName" to fullName,
        "email" to email,
        "phone" to phone,
        "location" to location.toMap(),
        "photo" to photo.toMap()
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = User(
            id = map["id"] as? Int ?: -1,
            fullName = map["fullName"] as String,
            email = map["email"] as String,
            phone = map["phone"] as String,
            location = Location.fromMap(map["location"] as? Map<String, Any> ?: emptyMap()),
            photo = AppImage.fromMap(map["photo"] as? Map<String, Any> ?: emptyMap())
        )
        fun emptyUser() : User {
            return User(
                id = 0,
                fullName = "",
                email = "",
                phone = "",
                location = Location.emptyLocation(),
                photo = AppImage.emptyAppImage()
            )
        }
    }
}
