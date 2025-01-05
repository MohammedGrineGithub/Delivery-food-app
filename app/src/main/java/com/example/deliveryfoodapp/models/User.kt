package com.example.deliveryfoodapp.models

import Location

@Suppress("UNCHECKED_CAST")
data class User(
    var id: Int,
    var fullName: String,
    var email: String,
    var phone: String,
    var location: Location,
    var photo: AppImage,
    var has_notification: Boolean,
    var carts: MutableList<UserCart>
) {

    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "fullName" to fullName,
        "email" to email,
        "phone" to phone,
        "location" to location.toMap(),
        "photo" to photo.toMap(),
        "has_notification" to has_notification,
        "carts" to carts.map { it.toMap() }
    )

    fun getUserCartByRestaurantID(restaurantID: Int): UserCart {

        var userCart : UserCart? = carts.find { it.restaurantID == restaurantID }
        if (userCart == null) {

            userCart = UserCart.emptyUserCart()

            // TODO userCart.id = newCartId  ## here i should create new user cart id in sqlLite and associate it here

            userCart.restaurantID = restaurantID
            return userCart
        }
        return userCart
    }

    private fun getUserCartIndexByRestaurantID(restaurantID: Int): Int {
        val index : Int? = carts.indexOfFirst { it.restaurantID == restaurantID }.takeIf { it != -1 }
        return index ?: -1
    }

    fun updateByUserCart(userCart : UserCart){
        val index = getUserCartIndexByRestaurantID(userCart.restaurantID)
        if (index == -1) {
            carts.add(userCart)
        }else {
            carts[index] = userCart.copy()
        }
    }

    fun deleteCart(userCart : UserCart){
        val index = getUserCartIndexByRestaurantID(userCart.restaurantID)
        if (index != -1) {
            carts.removeAt(index)
        }
    }

    companion object {
        fun fromMap(map: Map<String, Any>) = User(
            id = map["id"] as? Int ?: -1,
            fullName = map["fullName"] as? String ?: "",
            email = map["email"] as? String ?: "",
            phone = map["phone"] as? String ?: "",
            location = Location.fromMap(map["location"] as? Map<String, Any> ?: emptyMap()),
            photo = AppImage.fromMap(map["photo"] as? Map<String, Any> ?: emptyMap()),
            has_notification = map["has_notification"] as? Boolean ?: false,
            carts = (map["carts"] as? List<Map<String, Any>>)?.map {
                UserCart.fromMap(it)
            }?.toMutableList() ?: mutableListOf()
        )

        fun emptyUser(): User {
            return User(
                id = 0,
                fullName = "",
                email = "",
                phone = "",
                location = Location.emptyLocation(),
                photo = AppImage.emptyAppImage(),
                has_notification = false,
                carts = mutableListOf()
            )
        }
    }
}