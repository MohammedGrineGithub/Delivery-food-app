package com.example.deliveryfoodapp.models

data class Wilaya(
    var id: Int,
    var name: String
) {

    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name
        )
    }
    companion object {
        fun fromMap(map: Map<String, Any>): Wilaya {
            val id = map["id"] as? Int ?: -1
            val name = map["name"] as? String ?: "-1"
            return Wilaya(id, name)
        }
    }
}