package com.example.deliveryfoodapp.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.PrincipalButton

@Composable
fun OrderPlacedPage(navController : NavHostController) {

    BackHandler {
        navController.navigate(Routes.HOME_SCREEN) {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }
    }

    Column (
        modifier = Modifier.fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id=R.drawable.done),
            contentDescription = "validate order icons",
            modifier = Modifier.fillMaxWidth()
                .size(66.dp)
                .align(Alignment.CenterHorizontally),
            tint = Primary

        )

        Spacer(modifier = Modifier.height(18.dp))



        Text(
            text = "Order confirmed !",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        Spacer(modifier = Modifier.height(18.dp))


        Text(
            text = "Your order has been successfully placed !",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        )

        Spacer(modifier = Modifier.height(14.dp))


        Text(
            text = "\nCheck your orders page to view your order status!",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
            )
        )



        Spacer(modifier = Modifier.height(102.dp))

        PrincipalButton(
            text = "Back to Home",
            onClick = {
                navController.navigate(Routes.HOME_SCREEN){
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            }
        )

    }

}