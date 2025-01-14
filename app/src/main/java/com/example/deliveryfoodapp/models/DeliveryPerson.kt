package com.example.deliveryfoodapp.models
data class DeliveryPerson(
    var id : Int,
    var fullName: String,
    var phone: String,
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "full_name" to fullName,
        "phone" to phone,
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = DeliveryPerson(
            id = (map["id"] as Double).toInt(),
            fullName = map["full_name"] as String,
            phone = map["phone"] as String,
        )

        fun emptyDeliveryPerson() : DeliveryPerson {
            return DeliveryPerson(id =  0, fullName = "", phone = "")
        }
    }
}
