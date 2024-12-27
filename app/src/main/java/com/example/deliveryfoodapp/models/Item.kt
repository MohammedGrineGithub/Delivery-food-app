package com.example.deliveryfoodapp.models
data class Item(
    val name: String,
    val ingredients: String,
    val price: Double,
    val categoryId: Int,
    val photoId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name,
        "ingredients" to ingredients,
        "price" to price,
        "categoryId" to categoryId,
        "photoId" to photoId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Item(
            name = map["name"] as String,
            ingredients = map["ingredients"] as String,
            price = map["price"] as Double,
            categoryId = map["categoryId"] as Int,
            photoId = map["photoId"] as Int
        )
    }
}
