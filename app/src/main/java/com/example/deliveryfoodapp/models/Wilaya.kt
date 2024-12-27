package com.example.deliveryfoodapp.models

data class Wilaya(
    val id: Int,
    val name: String
) {

    // Method to convert an instance of Wilaya to a Map
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name
        )
    }

    // Companion object to hold the fromMap factory method
    companion object {
        // Method to create an instance of Wilaya from a Map
        fun fromMap(map: Map<String, Any>): Wilaya {
            val id = map["id"] as? Int ?: -1
            val name = map["name"] as? String ?: "-1"
            return Wilaya(id, name)
        }
    }
}