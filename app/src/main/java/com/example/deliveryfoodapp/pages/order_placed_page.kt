package com.example.deliveryfoodapp.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.PrincipalButton
import kotlinx.coroutines.delay


@Composable
fun OrderPlacedPage(navController: NavHostController) {





        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.done),
                contentDescription = "validate order icons",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally),
                tint = Color(0xFF058240)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Order confirmed !",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = lemonFontFamily
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Your order has been successfully placed",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row {
                Text(
                    text = "Click here to go to orders page",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "My orders page",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textDecoration = TextDecoration.Underline,
                        color = Color(0xFF3AA838)
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.MY_ORDERS_PAGE)
                    }
                )
            }

            Spacer(modifier = Modifier.height(102.dp))

            PrincipalButton("Back to Home",{ navController.navigate(Routes.HOME_PAGE)})
        }

}





































