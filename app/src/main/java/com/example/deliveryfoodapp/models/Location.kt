package com.example.deliveryfoodapp.models
data class Location(
    var id : Int,
    var address: String,
    var wilayaId: Int,
    var latitude: Double,
    var longitude: Double
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "address" to address,
        "wilayaId" to wilayaId,
        "latitude" to latitude,
        "longitude" to longitude
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Location(
            id = map["id"] as? Int ?: -1,
            address = map["address"] as String,
            wilayaId = map["wilayaId"] as Int,
            latitude = map["latitude"] as Double,
            longitude = map["longitude"] as Double
        )
        fun emptyLocation() : Location {
            return Location(
                id = 0,
                address = "",
                wilayaId = 0,
                latitude = 0.0,
                longitude = 0.0
            )
        }
    }
}
