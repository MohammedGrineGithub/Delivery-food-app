package com.example.deliveryfoodapp.models

data class RestaurantMenu(
    var id: Int,
    var categories: MutableList<Category>
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "categories" to categories.map { it.toMap() }
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = RestaurantMenu(
            id = map["id"] as? Int ?: -1,
            categories = (map["categories"] as? List<Map<String, Any>>)?.map {
                Category.fromMap(it)
            }?.toMutableList() ?: mutableListOf()
        )

        fun emptyRestaurantMenu(): RestaurantMenu {
            return RestaurantMenu(
                id = 0,
                categories = mutableListOf()
            )
        }
    }
}