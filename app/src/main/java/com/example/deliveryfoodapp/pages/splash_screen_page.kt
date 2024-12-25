package com.example.deliveryfoodapp.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryfoodapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set up navigation with NavController
            val navController = rememberNavController()

            // Navigation Host
            NavHost(navController = navController, startDestination = "splash_screen") {
                composable("splash_screen") {
                    SplashScreenPage(navController)
                }
            }
        }
    }
}

@Composable
fun SplashScreenPage(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Make the container fill the entire screen
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.Center) // Center the image within the Box
                .size(200.dp) // Set the desired size of the image
        )
    }
}

