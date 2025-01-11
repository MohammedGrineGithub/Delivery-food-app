package com.example.deliveryfoodapp.models
data class Comment(
    var id : Int,
    var comment: String,
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "comment" to comment,
    )

    companion object {
        fun fromMap(map: Map<String, Any?>) = Comment(
            id = (map["id"] as Double).toInt(),
            comment = map["comment"] as String,
        )
        fun emptyComment() : Comment {
            return Comment(id =  0, comment = "")
        }
    }
}
