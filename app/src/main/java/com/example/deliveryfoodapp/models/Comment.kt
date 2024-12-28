package com.example.deliveryfoodapp.models
data class Comment(
    val comment: String,
    val ratingId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "comment" to comment,
        "ratingId" to ratingId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Comment(
            comment = map["comment"] as String,
            ratingId = map["ratingId"] as Int
        )
    }
}
