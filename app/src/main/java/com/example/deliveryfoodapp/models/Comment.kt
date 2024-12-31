package com.example.deliveryfoodapp.models
data class Comment(
    var id : Int,
    var comment: String,
    var ratingId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "comment" to comment,
        "ratingId" to ratingId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Comment(
            id = map["id"] as? Int ?: -1,
            comment = map["comment"] as String,
            ratingId = map["ratingId"] as Int
        )
        fun emptyComment() : Comment {
            return Comment(id =  0, comment = "", ratingId = 0)
        }
    }
}
