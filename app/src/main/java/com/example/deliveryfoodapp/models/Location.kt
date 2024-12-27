package com.example.deliveryfoodapp.models
data class Location(
    val address: String,
    val wilayaId: Int,
    val latitude: Double,
    val longitude: Double
) {
    fun toMap(): Map<String, Any> = mapOf(
        "address" to address,
        "wilayaId" to wilayaId,
        "latitude" to latitude,
        "longitude" to longitude
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Location(
            address = map["address"] as String,
            wilayaId = map["wilayaId"] as Int,
            latitude = map["latitude"] as Double,
            longitude = map["longitude"] as Double
        )
    }
}
