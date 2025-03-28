package com.example.deliveryfoodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryfoodapp.models.Restaurant
import com.example.deliveryfoodapp.models.User
import com.example.deliveryfoodapp.pages.*
import com.example.deliveryfoodapp.ui.theme.DeliveryFoodAppTheme
import com.example.deliveryfoodapp.utils.*

var authenticatedUser : User = User.emptyUser()

var currentRestaurant : Restaurant = Restaurant.emptyRestaurant()

var currentOrderID = 0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeliveryFoodAppTheme {
                val navController: NavHostController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.SPLASH_SCREEN_PAGE,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.ONBOARDING_PAGE) { OnboardingPage(navController) }
                        composable(Routes.SPLASH_SCREEN_PAGE) { SplashScreenPage(navController) }
                        composable(Routes.LOGIN_PAGE) { LoginPage(navController) }
                        composable(Routes.SIGNUP_PAGE) { SignupPage(navController) }
                        composable(Routes.HOME_PAGE) { HomePage(navController) }
                        composable(Routes.HOME_SCREEN) { HomeScreen(navController) }
                        composable(Routes.NOTIFICATIONS_PAGE) { NotificationsPage(navController) }
                        composable(Routes.RESTAURANT_DETAILS_PAGE) { RestaurantDetailsPage(navController) }
                        composable(Routes.VALIDATE_PAYMENT_PAGE) { ValidatePaymentPage(navController) }
                        composable(Routes.ORDER_PLACED_PAGE) { OrderPlacedPage(navController) }
                        composable(Routes.PLUS_INFO_PAGE) { PlusInfoPage(navController) }
                        composable(Routes.MY_ORDERS_PAGE) { MyOrdersPage(navController) }
                        composable(Routes.MY_ORDERS_DETAILS_PAGE) { MyOrderDetailsPage(navController) }
                        composable(Routes.RATING_PAGE) { RatingPage(navController) }
                        composable(Routes.PROFILE_PAGE) { ProfilePage(navController) }
                        composable(Routes.MY_INFORMATION_PAGE) { MyInformationPage(navController) }
                        composable(Routes.CHANGE_PASSWORD_PAGE) { ChangePasswordPage(navController) }
                        composable(Routes.CHANGE_LOCATION_PAGE) { ChangeLocationPage(navController) }
                        composable(Routes.LOCATION_PAGE){ LocationPage(navController) }
                        composable(Routes.USER_CART_PAGE){ UserCartPage(navController) }
                    }
                }
            }
        }
    }
}