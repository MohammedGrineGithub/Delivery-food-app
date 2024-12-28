package com.example.deliveryfoodapp.models
data class Category(
    val name: String,
    val restaurantMenuId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name,
        "restaurantMenuId" to restaurantMenuId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Category(
            name = map["name"] as String,
            restaurantMenuId = map["restaurantMenuId"] as Int
        )
    }
}
