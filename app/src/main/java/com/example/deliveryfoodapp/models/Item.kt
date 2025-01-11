package com.example.deliveryfoodapp.models

data class Item(
    var id: Int,
    var name: String,
    var ingredients: String,
    var price: Double,
    var photo: AppImage
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "name" to name,
        "ingredients" to ingredients,
        "price" to price,
        "photo" to photo.toMap()
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Item(
            id = (map["id"] as Double).toInt(),
            name = map["name"] as? String ?: "",
            ingredients = map["ingredients"] as? String ?: "",
            price = map["price"] as? Double ?: 0.0,
            photo = (map["photo"] as? Map<String, Any>)?.let { AppImage.fromMap(it) }
                ?: AppImage.emptyAppImage()
        )

        fun emptyItem(): Item {
            return Item(
                id = 0,
                name = "",
                ingredients = "",
                price = 0.0,
                photo = AppImage.emptyAppImage()
            )
        }
    }
}