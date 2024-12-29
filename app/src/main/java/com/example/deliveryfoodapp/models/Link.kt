package com.example.deliveryfoodapp.models
data class Link(
    var id : Int,
    var name: String,
    var url: String,
    var restaurantId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "name" to name,
        "url" to url,
        "restaurantId" to restaurantId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Link(
            id = map["id"] as? Int ?: -1,
            name = map["name"] as String,
            url = map["url"] as String,
            restaurantId = map["restaurantId"] as Int
        )
    }
}
