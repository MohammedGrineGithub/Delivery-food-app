package com.example.deliveryfoodapp.models

data class Rating(
    val reviewersCount: Int,
    val rating: Double
) {
    fun toMap(): Map<String, Any> = mapOf(
        "reviewersCount" to reviewersCount,
        "rating" to rating
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Rating(
            reviewersCount = map["reviewersCount"] as Int,
            rating = map["rating"] as Double
        )
    }
}
