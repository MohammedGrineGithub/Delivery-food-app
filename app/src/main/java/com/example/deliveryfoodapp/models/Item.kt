package com.example.deliveryfoodapp.models
data class Item(
    var id : Int,
    var name: String,
    var ingredients: String,
    var price: Double,
    var categoryId: Int,
    var photoId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "name" to name,
        "ingredients" to ingredients,
        "price" to price,
        "categoryId" to categoryId,
        "photoId" to photoId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Item(
            id = map["id"] as? Int ?: -1,
            name = map["name"] as String,
            ingredients = map["ingredients"] as String,
            price = map["price"] as Double,
            categoryId = map["categoryId"] as Int,
            photoId = map["photoId"] as Int
        )

        fun emptyItem() : Item {
            return Item(id = 0, name = "", ingredients = "", price = 0.0, categoryId = 0, photoId = 0)
        }
    }
}
