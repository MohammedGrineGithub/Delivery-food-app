package com.example.deliveryfoodapp.models

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Suppress("UNCHECKED_CAST")
data class Restaurant(
    var id : Int,
    var restaurantName: String,
    var logo: AppImage,
    var bannerLogo: AppImage,
    var location: Location,
    var cuisineType: CuisineType,
    var rating: Rating,
    var phone: String,
    var email: String,
    var deliveryPrice: Double,
    var deliveryDuration: LocalTime,
    var menu: RestaurantMenu,
    var openingTime: LocalDateTime,
    var closingTime: LocalDateTime
) {

    @SuppressLint("NewApi")
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "restaurantName" to restaurantName,
            "logo" to logo.toMap(),
            "bannerLogo" to bannerLogo.toMap(),
            "location" to location.toMap(),
            "cuisineType" to cuisineType.toMap(),
            "rating" to rating.toMap(),
            "phone" to phone,
            "email" to email,
            "deliveryPrice" to deliveryPrice,
            "deliveryDuration" to deliveryDuration.toString(),
            "menu" to menu.toMap(),
            "openingTime" to openingTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "closingTime" to closingTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        )
    }


    companion object {

        @SuppressLint("NewApi")
        fun fromMap(map: Map<String, Any>): Restaurant {
            val id = map["id"] as? Int ?: -1
            val restaurantName = map["restaurantName"] as? String ?: ""
            val logo = AppImage.fromMap(map["logo"] as? Map<String, Any> ?: emptyMap())
            val bannerLogo = AppImage.fromMap(map["bannerLogo"] as? Map<String, Any> ?: emptyMap())
            val location = Location.fromMap(map["location"] as? Map<String, Any> ?: emptyMap())
            val cuisineType = CuisineType.fromMap(map["cuisineType"] as? Map<String, Any> ?: emptyMap())
            val rating = Rating.fromMap(map["rating"] as? Map<String, Any> ?: emptyMap())
            val phone = map["phone"] as? String ?: ""
            val email = map["email"] as? String ?: ""
            val deliveryPrice = map["deliveryPrice"] as? Double ?: 0.0
            val deliveryDuration = LocalTime.parse(map["deliveryDuration"] as? String ?: "00:00:00")
            val menu = RestaurantMenu.fromMap(map["menu"] as? Map<String, Any> ?: emptyMap())
            val openingTime = LocalDateTime.parse(map["openingTime"] as? String ?: "2000-01-01T00:00:00")
            val closingTime = LocalDateTime.parse(map["closingTime"] as? String ?: "2000-01-01T00:00:00")

            return Restaurant(
                id, restaurantName, logo, bannerLogo, location, cuisineType, rating, phone, email,
                deliveryPrice, deliveryDuration, menu, openingTime, closingTime
            )
        }

        @SuppressLint("NewApi")
        fun emptyRestaurant(): Restaurant {
            return Restaurant(
                id = 0,
                restaurantName = "",
                logo = AppImage.emptyAppImage(),
                bannerLogo = AppImage.emptyAppImage(),
                location = Location.emptyLocation(),
                cuisineType = CuisineType.emptyCusineType(),
                rating = Rating.emptyRating(),
                phone = "",
                email = "",
                deliveryPrice = 0.0,
                deliveryDuration = LocalTime.MIDNIGHT,
                menu = RestaurantMenu.emptyRestaurantMenu(),
                openingTime = LocalDateTime.MIN,
                closingTime = LocalDateTime.MIN
            )
        }
    }
}
