package com.example.deliveryfoodapp.models

data class Rating(
    var id : Int,
    var reviewersCount: Int,
    var rating: Double,
    var comments : MutableList<Comment> = mutableListOf()
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "reviewersCount" to reviewersCount,
        "rating" to rating,
        "comments" to comments.map { it.toMap() }
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Rating(
            id = map["id"] as? Int ?: -1,
            reviewersCount = map["reviewersCount"] as Int,
            rating = map["rating"] as Double,
            comments = (map["comments"] as? List<Map<String, Any>>)?.map {
                Comment.fromMap(it)
            }?.toMutableList() ?: mutableListOf()
        )
        fun emptyRating() : Rating {
            return Rating(id = 0, reviewersCount = 0, rating = 0.0)
        }
    }
}
