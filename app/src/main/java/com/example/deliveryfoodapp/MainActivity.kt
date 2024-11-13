package com.example.deliveryfoodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryfoodapp.pages.*
import com.example.deliveryfoodapp.ui.theme.DeliveryFoodAppTheme

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
                        startDestination = "MainApp",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("MainApp") { MainApp(modifier = Modifier.padding(innerPadding),navController = navController) }
                        composable("OnboardingPage") { OnboardingPage(navController) }
                        composable("LoginPage") { LoginPage(navController) }
                        composable("SignupPage") { SignupPage(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun MainApp(navController : NavHostController, modifier: Modifier = Modifier) {

    // here we get the token and check if the user is authenticated or not
    Text("main page")
}