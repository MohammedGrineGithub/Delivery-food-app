package com.example.deliveryfoodapp.models
data class CuisineType(
    var id : Int,
    var name: String
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "name" to name
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = CuisineType(
            id = map["id"] as? Int ?: -1,
            name = map["name"] as String
        )
        fun emptyCuisineType() : CuisineType {
            return CuisineType(id =  0, name = "")
        }
    }
}
