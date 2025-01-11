package com.example.deliveryfoodapp.models

data class Rating(
    var id : Int,
    var reviewersCount: Int,
    var rating: Double
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "reviewersCount" to reviewersCount,
        "rating" to rating
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Rating(
            id = (map["id"] as Double).toInt() ?: 0,
            reviewersCount = map["reviewersCount"] as? Int ?: 0,
            rating = map["rating"] as Double ?: 0.0
        )
        fun emptyRating() : Rating {
            return Rating(id = 0, reviewersCount = 0, rating = 0.0)
        }
    }
}
