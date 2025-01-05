package com.example.deliveryfoodapp.utils

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import com.example.deliveryfoodapp.models.*
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.Blue
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.ui.theme.Secondary
import java.time.LocalTime


@SuppressLint("NewApi")
fun createRestaurantForTest() : Restaurant {

    val banner : AppImage = AppImage.emptyAppImage()
    banner.imagePath = "https://img.freepik.com/free-photo/mexican-tacos-with-beef-tomato-sauce-salsa_2829-14221.jpg?t=st=1735567764~exp=1735571364~hmac=df541b39fef6a400c9c01f923b974012be436860b499b3e9f761354d409597b1&w=740"
    val logo : AppImage = AppImage.emptyAppImage()

    logo.imagePath = "https://th.bing.com/th/id/OIP.M9t7f55K3088Y3ZCGxUATgHaHa?rs=1&pid=ImgDetMain"

    val categories: MutableList<Category> = mutableListOf(
        Category(1, "Pizza", mutableListOf(
            Item(1, "Margherita", "Tomatoes, Mozzarella, Basil", 400.0, AppImage(1, banner.imagePath)),
            Item(2, "Pepperoni", "Tomatoes, Mozzarella, Pepperoni", 450.0, AppImage(2, banner.imagePath)),
            Item(3, "Vegetarian", "Tomatoes, Mozzarella, Mixed Vegetables", 420.0, AppImage(3, banner.imagePath)),
            Item(4, "BBQ Chicken", "BBQ Sauce, Chicken, Red Onion", 480.0, AppImage(4, banner.imagePath))
        )),

        Category(2, "Tacos", mutableListOf(
            Item(5, "Beef Taco", "Ground Beef, Lettuce, Cheese, Salsa", 250.0, AppImage(5, banner.imagePath)),
            Item(7, "Fish Taco", "Grilled Fish, Lettuce, Cabbage, Lime", 270.0, AppImage(7, banner.imagePath)),
            Item(8, "Vegetarian Taco", "Beans, Rice, Lettuce, Guacamole", 240.0, AppImage(8, banner.imagePath))
        )),

        Category(3, "Burger", mutableListOf(
            Item(9, "Classic Burger", "Beef Patty, Lettuce, Tomato, Cheese", 500.0, AppImage(9, banner.imagePath)),
            Item(10, "Cheese Burger", "Beef Patty, Lettuce, Tomato, Extra Cheese", 550.0, AppImage(10, banner.imagePath)),
            Item(11, "Chicken Burger", "Grilled Chicken, Lettuce, Mayo", 480.0, AppImage(11, banner.imagePath)),
            Item(12, "Veggie Burger", "Vegetable Patty, Lettuce, Tomato, Vegan Mayo", 460.0, AppImage(12, banner.imagePath))
        )),

        Category(4, "Plates", mutableListOf(
            Item(13, "Spaghetti Bolognese", "Pasta, Beef Bolognese Sauce", 700.0, AppImage(13, banner.imagePath)),
            Item(14, "Chicken Alfredo", "Pasta, Grilled Chicken, Alfredo Sauce", 750.0, AppImage(14, banner.imagePath)),
            Item(15, "Fish & Chips", "Fried Fish, French Fries, Tartare Sauce", 800.0, AppImage(15, banner.imagePath)),
        )),

        Category(5, "Sandwiches", mutableListOf(
            Item(17, "Grilled Cheese", "Cheese, Toasted Bread", 220.0, AppImage(17, banner.imagePath)),
            Item(18, "BLT Sandwich", "Bacon, Lettuce, Tomato, Mayo", 250.0, AppImage(18, banner.imagePath)),
            Item(19, "Chicken Club", "Grilled Chicken, Bacon, Lettuce, Tomato, Mayo", 300.0, AppImage(19, banner.imagePath)),
            Item(20, "Veggie Sandwich", "Avocado, Lettuce, Tomato, Cucumber, Veggie Mayo", 280.0, AppImage(20, banner.imagePath))
        )),

        Category(6, "Salade", mutableListOf(
            Item(21, "Caesar Salad", "Lettuce, Chicken, Caesar Dressing, Parmesan", 350.0, AppImage(21, banner.imagePath)),
            Item(22, "Greek Salad", "Tomatoes, Cucumbers, Olives, Feta, Olive Oil", 330.0, AppImage(22, banner.imagePath)),
        ))
    )
    val menu : RestaurantMenu = RestaurantMenu.emptyRestaurantMenu()

    menu.categories = categories


    val restaurant : Restaurant = Restaurant.emptyRestaurant()
    restaurant.id = 1
    restaurant.banner = banner
    restaurant.logo = logo
    restaurant.restaurantName = "American Burger"
    restaurant.location = Location.emptyLocation()
    restaurant.location.address = "Rue Nadjet Slimane, Kouba, Algiers"
    restaurant.cuisineType = CuisineType(id=0, name = "American")
    restaurant.deliveryDuration = 30
    restaurant.deliveryPrice = 300
    restaurant.rating = Rating(id=0, reviewersCount = 209, rating = 4.5)
    restaurant.menu = menu
    return restaurant
}

fun createUserForTest() : User {
    val user : User = User.emptyUser()

    val items = mutableListOf(
        Item(1, "Margherita", "Tomatoes, Mozzarella, Basil", 400.0, AppImage.emptyAppImage()),
        Item(2, "Pepperoni", "Tomatoes, Mozzarella, Pepperoni", 450.0, AppImage.emptyAppImage()),
        Item(3, "Vegetarian", "Tomatoes, Mozzarella, Mixed Vegetables", 420.0, AppImage.emptyAppImage()),
        Item(4, "BBQ Chicken", "BBQ Sauce, Chicken, Red Onion", 480.0, AppImage.emptyAppImage())
    )
    val orderItems = mutableListOf(
        OrderItem(id = 1, item = items[0], note = "Extra cheese", itemQuantity = 2),
        OrderItem(id = 2, item = items[1], note = "No pepperoni", itemQuantity = 1),
        OrderItem(id = 3, item = items[2], note = "Add olives", itemQuantity = 3),
        OrderItem(id = 4, item = items[3], note = "Extra BBQ sauce", itemQuantity = 1)
    )
    val cart = UserCart(
        id = 1,
        orderItems = orderItems,
        restaurantID = 1
    )

    user.carts.add(cart)
    user.id = 1
    return user
}

data class OrderTest(
    val id: String,
    val restaurantName: String,
    val price: String,
    val orderedAtTime: String,
    val orderedAtDate: String,
    val status: String,
    val imageUrl: String,
    val statusId: Color
)

fun createOrdersForTest() : List<OrderTest> {

    val imagePath1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKM4bEp3laJBx99q9bKpyDm_m1uQGF1r0UMg&s"
    val imagePath2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3a9bC67pnkc3h7JTXGnooTHeez0qpL6-9nQ&s"
    val imagePath3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyPuQ1_d2pRT9JOC1_6CbRZoSyeXJGijM7rA&s"
    val imagePath4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2OSPi0oehcfsWEsJaCulFc7wU9VIN170d1g&s"
    // A sample list of orders
    val sampleOrders = listOf(
        OrderTest(
            id = "1",
            restaurantName = "Pizzeria Woodpecker",
            price = "2700 DA",
            orderedAtTime = "08:30 p.m.",
            orderedAtDate = "08/12/2024",
            status = "On the way",
            imageUrl = imagePath1,
            statusId = Blue
        ),
        OrderTest(
            id = "2",
            restaurantName = "Tacozza",
            price = "700 DA",
            orderedAtTime = "11:30 a.m.",
            orderedAtDate = "08/12/2024",
            status = "IsPrepared",
            imageUrl = imagePath2,
            statusId = BlackStroke
        ),
        OrderTest(
            id = "3",
            restaurantName = "American Burger",
            price = "1800 DA",
            orderedAtTime = "12:00 a.m.",
            orderedAtDate = "10/11/2024",
            status = "Canceled",
            imageUrl = imagePath3,
            statusId = Red
        ),
        OrderTest(
            id = "4",
            restaurantName = "La Casa del Burger",
            price = "1200 DA",
            orderedAtTime = "12:10 a.m.",
            orderedAtDate = "10/11/2024",
            status = "Delivered",
            imageUrl = imagePath4,
            statusId = Secondary
        ),
        OrderTest(
            id = "1",
            restaurantName = "Pizzeria Woodpecker",
            price = "2700 DA",
            orderedAtTime = "08:30 p.m.",
            orderedAtDate = "08/12/2024",
            status = "On the way",
            imageUrl = imagePath1,
            statusId = Blue
        ),
        OrderTest(
            id = "2",
            restaurantName = "Tacozza",
            price = "700 DA",
            orderedAtTime = "11:30 a.m.",
            orderedAtDate = "08/12/2024",
            status = "IsPrepared",
            imageUrl = imagePath2,
            statusId = BlackStroke
        ),
        OrderTest(
            id = "3",
            restaurantName = "American Burger",
            price = "1800 DA",
            orderedAtTime = "12:00 a.m.",
            orderedAtDate = "10/11/2024",
            status = "Canceled",
            imageUrl = imagePath3,
            statusId = Red
        ),
        OrderTest(
            id = "4",
            restaurantName = "La Casa del Burger",
            price = "1200 DA",
            orderedAtTime = "12:10 a.m.",
            orderedAtDate = "10/11/2024",
            status = "Delivered",
            imageUrl = imagePath4,
            statusId = Primary
        )
    )
    return sampleOrders
}