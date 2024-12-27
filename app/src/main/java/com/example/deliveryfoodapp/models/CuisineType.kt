package com.example.deliveryfoodapp.models
data class CuisineType(
    val name: String
) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = CuisineType(
            name = map["name"] as String
        )
    }
}
