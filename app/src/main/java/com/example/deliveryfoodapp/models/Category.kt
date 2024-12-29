package com.example.deliveryfoodapp.models
data class Category(
    var id : Int,
    var name: String,
    var restaurantMenuId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "name" to name,
        "restaurantMenuId" to restaurantMenuId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Category(
            id = map["id"] as? Int ?: -1,
            name = map["name"] as String,
            restaurantMenuId = map["restaurantMenuId"] as Int
        )
        fun emptyCategory() : Category {
            return Category(id = 0, name = "", restaurantMenuId = 0)
        }
    }
}
