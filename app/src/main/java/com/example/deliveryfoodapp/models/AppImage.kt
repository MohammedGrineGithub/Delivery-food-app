package com.example.deliveryfoodapp.models
data class AppImage(
    val imagePath: String
) {
    fun toMap(): Map<String, Any> = mapOf(
        "imagePath" to imagePath
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = AppImage(
            imagePath = map["imagePath"] as String
        )
    }
}
