package com.example.deliveryfoodapp.models
data class AppImage(
    var id : Int,
    var imagePath: String
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "imagePath" to imagePath
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = AppImage(
            id = map["id"] as? Int ?: -1,
            imagePath = map["imagePath"] as String
        )
        fun emptyAppImage() : AppImage {
            return AppImage(id =  0, imagePath = "")
        }
    }
}
