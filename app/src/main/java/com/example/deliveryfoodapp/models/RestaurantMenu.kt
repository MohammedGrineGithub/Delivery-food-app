package com.example.deliveryfoodapp.models

data class RestaurantMenu(
    var id: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = RestaurantMenu(
            id = map["id"] as? Int ?: -1,
        )
        fun emptyRestaurantMenu() : RestaurantMenu {
            return RestaurantMenu(id = 0)
        }
    }
}
