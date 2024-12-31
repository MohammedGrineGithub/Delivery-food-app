package com.example.deliveryfoodapp.utils

import androidx.compose.ui.graphics.Color
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.ui.theme.Blue
import com.example.deliveryfoodapp.ui.theme.Secondary


class Routes {
    companion object {
        const val MAIN_APP = "MainApp"
        const val ONBOARDING_PAGE = "OnboardingPage"
        const val SPLASH_SCREEN_PAGE = "splash_screen_page"
        const val LOGIN_PAGE = "LoginPage"
        const val SIGNUP_PAGE = "SignupPage"
        const val HOME_PAGE = "HomePage"
        const val HOME_SCREEN = "HomeScreen"
        const val NOTIFICATIONS_PAGE = "NotificationsPage"
        const val RESTAURANT_DETAILS_PAGE = "RestaurantDetailsPage"
        const val VALIDATE_PAYMENT_PAGE = "ValidatePaymentPage"
        const val PLUS_INFO_PAGE = "PlusInfoPage"
        const val MY_ORDERS_PAGE = "MyOrdersPage"
        const val MY_ORDERS_DETAILS_PAGE = "MyOrderDetailsPage"
        const val PROFILE_PAGE = "ProfilePage"
        const val MY_INFORMATION_PAGE = "MyInformationPage"
        const val CHANGE_PASSWORD_PAGE = "ChangePasswordPage"
        const val CHANGE_LOCATION_PAGE = "ChangeLocationPage"
        const val ORDER_PLACED_PAGE = "OrderPlacedPage"
        const val RATING_PAGE = "RatingPage"
        const val LOCATION_PAGE = "LocationPage"
    }
}

class Titles {
    companion object {

    }
}

data class ImageResource(
    val id: String,
    val url: String
)
object ImageResources {
    val images = listOf(
        ImageResource(id = "restaurant_image1", url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4TBsu0IwLlG6glyx3hrpDoZISXb2mU534Xp6UcI3Q6j4Zo7t2Rv1JEA3TzbdTeBV1gUw&usqp=CAU"),
        ImageResource(id = "restaurant_image2", url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3a9bC67pnkc3h7JTXGnooTHeez0qpL6-9nQ&s"),
        ImageResource(id = "restaurant_image3", url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyPuQ1_d2pRT9JOC1_6CbRZoSyeXJGijM7rA&s"),
        ImageResource(id = "restaurant_image4", url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2OSPi0oehcfsWEsJaCulFc7wU9VIN170d1g&s")
    )
}


data class Order(
    val id: String,
    val restaurantName: String,
    val price: String,
    val orderedAtTime: String,
    val orderedAtDate: String,
    val status: String,
    val imageUrl: String,
    val statusId: Color
)


// A sample list of orders
val sampleOrders = mutableListOf(
    Order(
        id = "1",
        restaurantName = "Pizzeria Woodpecker",
        price = "2700 DA",
        orderedAtTime = "08:30 p.m.",
        orderedAtDate = "08/12/2024",
        status = "On the way",
        imageUrl = ImageResources.images[0].url,
        statusId = Blue
    ),
    Order(
        id = "2",
        restaurantName = "Tacozza",
        price = "700 DA",
        orderedAtTime = "11:30 a.m.",
        orderedAtDate = "08/12/2024",
        status = "IsPrepared",
        imageUrl = ImageResources.images[1].url,
        statusId = BlackStroke
    ),
    Order(
        id = "3",
        restaurantName = "American Burger",
        price = "1800 DA",
        orderedAtTime = "12:00 a.m.",
        orderedAtDate = "10/11/2024",
        status = "Canceled",
        imageUrl = ImageResources.images[2].url,
        statusId = Red
    ),
    Order(
        id = "4",
        restaurantName = "La Casa del Burger",
        price = "1200 DA",
        orderedAtTime = "12:10 a.m.",
        orderedAtDate = "10/11/2024",
        status = "Delivered",
        imageUrl = ImageResources.images[3].url,
        statusId = Secondary
    ),
    Order(
        id = "1",
        restaurantName = "Pizzeria Woodpecker",
        price = "2700 DA",
        orderedAtTime = "08:30 p.m.",
        orderedAtDate = "08/12/2024",
        status = "On the way",
        imageUrl = ImageResources.images[0].url,
        statusId = Blue
    ),
    Order(
        id = "2",
        restaurantName = "Tacozza",
        price = "700 DA",
        orderedAtTime = "11:30 a.m.",
        orderedAtDate = "08/12/2024",
        status = "IsPrepared",
        imageUrl = ImageResources.images[1].url,
        statusId = BlackStroke
    ),
    Order(
        id = "3",
        restaurantName = "American Burger",
        price = "1800 DA",
        orderedAtTime = "12:00 a.m.",
        orderedAtDate = "10/11/2024",
        status = "Canceled",
        imageUrl = ImageResources.images[2].url,
        statusId = Red
    ),
    Order(
        id = "4",
        restaurantName = "La Casa del Burger",
        price = "1200 DA",
        orderedAtTime = "12:10 a.m.",
        orderedAtDate = "10/11/2024",
        status = "Delivered",
        imageUrl = ImageResources.images[3].url,
        statusId = Secondary
    )
)
