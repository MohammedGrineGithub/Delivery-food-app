package com.example.deliveryfoodapp.models

data class RestaurantMenu(
    var id: Int,
    var categories: List<Category> // Use List<Category> instead of List<Any>
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "categories" to categories.map { it.toMap() }
    )

    companion object {
        fun fromMap(map: Map<String, Any>): RestaurantMenu {
            return RestaurantMenu(
                id = map["id"] as? Int ?: -1,
                categories = (map["categories"] as? List<Map<String, Any>>)?.map {
                    Category.fromMap(it)
                } ?: emptyList()
            )
        }

        fun emptyRestaurantMenu(): RestaurantMenu {
            return RestaurantMenu(
                id = 0,
                categories = emptyList()
            )
        }
    }
}