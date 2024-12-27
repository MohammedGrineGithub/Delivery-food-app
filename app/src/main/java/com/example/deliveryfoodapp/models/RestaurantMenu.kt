package com.example.deliveryfoodapp.models

data class RestaurantMenu(
    val id: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = RestaurantMenu(
            id = map["id"] as Int
        )
    }
}
