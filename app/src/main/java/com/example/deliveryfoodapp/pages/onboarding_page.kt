package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.deliveryfoodapp.R


@Composable
fun OnboardingPage(navController: NavHostController) {
    Text("MAKLAEXPRESS",
        color = colorResource(id = R.color.PRIMARY_GREEN),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )
    Box(
        modifier = Modifier
            .fillMaxSize() // Makes the Box take up the entire available space
    ) {
        Image(
            painter = painterResource(id = R.drawable.onboarding_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.Center) // Center the image within the Box
                .size(500.dp)
        )
    }
    Text("Welcome to MAKLAEXPRESS  the best delivery food app in Algeria. You can order food from any restaurant you want, place orders with variant of items and explore the best, fast and efficient delivery service",
        //color = colorResource(id = R.color.PRIMARY_GREEN),
        fontWeight = FontWeight.Bold,
        //fontSize = 30.sp
    )
    Button(onClick = {}) {
        Text("Start")
    }
}