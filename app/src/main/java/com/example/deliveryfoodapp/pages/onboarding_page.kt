package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.widgets.PrincipalButton


@Composable
fun OnboardingPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen space
            .padding(16.dp), // Add some padding around the column
        horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
        //verticalArrangement = Arrangement.SpaceEvenly // Space out items evenly
    ) {
        // 1. Title Text
        Text(
            "MAKLAEXPRESS",
            modifier = Modifier.padding(top = 50.dp),
            color = Primary,
            fontFamily = lemonFontFamily,
            fontSize = 36.sp
        )

        // 2. Onboarding Image
        Image(
            painter = painterResource(id = R.drawable.onboarding_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(400.dp) // Set the image size
        )

        // 3. Welcome Text
        Text(
            "Welcome to MAKLAEXPRESS, the best delivery food app in Algeria. You can order food from any restaurant you want, place orders with a variety of items, and explore the best, fast, and efficient delivery service.",
            modifier = Modifier.padding(bottom = 30.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            textAlign = TextAlign.Center // Center the text within the column,

        )

        // 4. Start Button
        PrincipalButton(
            text = "Start",
            onClick = {navController.navigate(Routes.LOGIN_PAGE)},
        )
    }
}
