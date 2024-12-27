package com.example.deliveryfoodapp.models
data class Link(
    val name: String,
    val url: String,
    val restaurantId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "name" to name,
        "url" to url,
        "restaurantId" to restaurantId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Link(
            name = map["name"] as String,
            url = map["url"] as String,
            restaurantId = map["restaurantId"] as Int
        )
    }
}
