package com.example.deliveryfoodapp.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.alpha
import com.example.deliveryfoodapp.utils.*

@Composable
fun SplashScreenPage(navController: NavHostController) {

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f, animationSpec = tween(300))
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
                .align(Alignment.Center)
                .size(200.dp)
                .alpha(alpha = alpha.value)
        )
    }
}


