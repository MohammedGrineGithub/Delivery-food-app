package com.example.deliveryfoodapp.pages

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
import com.example.deliveryfoodapp.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.deliveryfoodapp.utils.*

@Composable
fun SplashScreenPage(navController: NavHostController) {

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(300)
        navController.navigate(Routes.ONBOARDING_PAGE)
    }

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


