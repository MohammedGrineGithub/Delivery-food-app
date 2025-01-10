package com.example.deliveryfoodapp.models
data class SocialMediaLink(
    var id : Int,
    var name: String,
    var url: String,
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "name" to name,
        "url" to url,
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = SocialMediaLink(
            id = map["id"] as? Int ?: -1,
            name = map["name"] as String,
            url = map["url"] as String,
        )
    }
}
