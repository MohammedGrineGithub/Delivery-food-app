package com.example.deliveryfoodapp.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.utils.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreenPage(navController: NavHostController) {

    val context = LocalContext.current
    Pref.context = context

    val startDestination: String
    if (Pref.isFirstTime()) {
        Pref.setFirstTime(false)
        startDestination = Routes.ONBOARDING_PAGE
    } else {
        startDestination = if (Pref.getUserID() == -1) {
            Routes.LOGIN_PAGE
        } else {
            Routes.HOME_SCREEN
        }
    }

    // Only alpha animation for fade-in effect
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f, animationSpec = tween(300)) // Fade-in animation
        delay(300) // Wait for 300ms
        navController.navigate(startDestination) {
            popUpTo(Routes.SPLASH_SCREEN_PAGE) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp)
                .alpha(alpha = alpha.value) // Apply alpha animation
        )
    }
}