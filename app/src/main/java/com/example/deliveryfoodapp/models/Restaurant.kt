package com.example.deliveryfoodapp.models

data class Restaurant(
    val restaurantName: String,
    val logoId: Int,
    val bannerLogoId: Int,
    val locationId: Int,
    val cuisineTypeId: Int,
    val ratingId: Int,
    val phone: String,
    val email: String,
    val deliveryPrice: Double,
    val deliveryDuration: String,
    val menuId: Int,
    val openingTime: String,
    val closingTime: String
) {
    fun toMap(): Map<String, Any> = mapOf(
        "restaurantName" to restaurantName,
        "logoId" to logoId,
        "bannerLogoId" to bannerLogoId,
        "locationId" to locationId,
        "cuisineTypeId" to cuisineTypeId,
        "ratingId" to ratingId,
        "phone" to phone,
        "email" to email,
        "deliveryPrice" to deliveryPrice,
        "deliveryDuration" to deliveryDuration,
        "menuId" to menuId,
        "openingTime" to openingTime,
        "closingTime" to closingTime
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Restaurant(
            restaurantName = map["restaurantName"] as String,
            logoId = map["logoId"] as Int,
            bannerLogoId = map["bannerLogoId"] as Int,
            locationId = map["locationId"] as Int,
            cuisineTypeId = map["cuisineTypeId"] as Int,
            ratingId = map["ratingId"] as Int,
            phone = map["phone"] as String,
            email = map["email"] as String,
            deliveryPrice = map["deliveryPrice"] as Double,
            deliveryDuration = map["deliveryDuration"] as String,
            menuId = map["menuId"] as Int,
            openingTime = map["openingTime"] as String,
            closingTime = map["closingTime"] as String
        )
    }
}
