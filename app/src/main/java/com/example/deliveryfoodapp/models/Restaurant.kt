package com.example.deliveryfoodapp.models

import Location
import android.annotation.SuppressLint
import com.example.deliveryfoodapp.utils.CuisineTypes
import java.time.LocalTime
import java.time.format.DateTimeFormatter
@Suppress("UNCHECKED_CAST")
data class Restaurant(
    var id : Int,
    var restaurantName: String,
    var logo: AppImage,
    var banner: AppImage,
    var location: Location,
    var cuisineType: CuisineType,
    var rating: Rating,
    var phone: String,
    var email: String,
    var deliveryPrice: Int,
    var deliveryDuration: Int,
    var menu: RestaurantMenu,
    var openingTime: LocalTime,
    var closingTime: LocalTime,
    var deliveryPersons : MutableList<DeliveryPerson> = mutableListOf(),
    var socialMediaLinks : MutableList<SocialMediaLink> = mutableListOf()
) {

    @SuppressLint("NewApi")
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "restaurant_name" to restaurantName,
            "logo" to logo.toMap(),
            "banner_logo" to banner.toMap(),
            "location" to location.toMap(),
            "cuisine_type" to cuisineType.toMap(),
            "rating" to rating.toMap(),
            "phone" to phone,
            "email" to email,
            "delivery_price" to deliveryPrice,
            "delivery_duration" to deliveryDuration,
            "menu" to menu.toMap(),
            "opening_time" to openingTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "closing_time" to closingTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "deliveryPersons" to deliveryPersons.map { it.toMap() },
            "socialMediaLinks" to socialMediaLinks.map { it.toMap() }
        )
    }

    fun getDeliveryPersonByItID(deliveryPersonID : Int) : DeliveryPerson {
        val deliveryPerson : DeliveryPerson? = deliveryPersons.find { it.id == deliveryPersonID }
        return deliveryPerson ?: DeliveryPerson.emptyDeliveryPerson()
    }


    companion object {

        // TODO : When i cast the map into double than user to int, make a test if i can cast it into a double, then if i can i will cast it

        @SuppressLint("NewApi")
        fun fromMap(map: Map<String, Any?>): Restaurant {
            val id = (map["id"] as Double).toInt() ?: 0
            val restaurantName = map["restaurant_name"] as? String ?: ""
            val logo = AppImage.fromMap(map["logo"] as? Map<String, Any> ?: emptyMap())
            val bannerLogo = AppImage.fromMap(map["banner_logo"] as? Map<String, Any> ?: emptyMap())
            val location = Location.fromMap(map["location"] as? Map<String, Any> ?: emptyMap())
            val cuisineType = CuisineTypes.getCuisineTypeByCuisineTypeID((map["cuisine_type"] as Double).toInt() ?: 0)
            val rating = Rating.fromMap(map["rating"] as? Map<String, Any> ?: emptyMap())
            val phone = map["phone"] as? String ?: ""
            val email = map["email"] as? String ?: ""
            val deliveryPrice = map["delivery_price"] as? Int ?: 0
            val deliveryDuration = map["delivery_duration"] as? Int ?: 0
            /*
            val deliveryPrice = (map["delivery_price"] as Double).toInt()
            val deliveryDuration = (map["delivery_price"] as Double).toInt()
             */
            val menuMap : MutableMap<String, Any?> = mutableMapOf()
            menuMap["id"] = (map["menu"] as Double).toInt()
            val menu = RestaurantMenu.fromMap(menuMap)
            val openingTime = LocalTime.parse(map["opening_time"] as? String ?: "2000-01-01T00:00:00")
            val closingTime = LocalTime.parse(map["closing_time"] as? String ?: "2000-01-01T00:00:00")

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
                banner = AppImage.emptyAppImage(),
                location = Location.emptyLocation(),
                cuisineType = CuisineType.emptyCuisineType(),
                rating = Rating.emptyRating(),
                phone = "",
                email = "",
                deliveryPrice = 0,
                deliveryDuration = 0,
                menu = RestaurantMenu.emptyRestaurantMenu(),
                openingTime = LocalTime.MIN,
                closingTime = LocalTime.MIN
            )
        }
    }
}
