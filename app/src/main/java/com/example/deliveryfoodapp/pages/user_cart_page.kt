package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import com.example.deliveryfoodapp.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.ui.theme.PrimaryFill
import com.example.deliveryfoodapp.widgets.CustomAppBar
import com.example.deliveryfoodapp.widgets.PrincipalButton


@Composable
fun UserCartPage(navController : NavHostController){

    var totalItemsNumber = remember { mutableIntStateOf(2) }
    var totalItemsPrice = remember { mutableDoubleStateOf(1200.0) }

    /** Main Page **/
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(all = 20.dp)
    ) {
        /** AppBar + Main content **/
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ){
            /** AppBar (Title + back arrow icon) **/
            CustomAppBar(
                text = "My Cart",
                onClick = {navController.popBackStack()}
            )

            /** Main content **/
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ){
                /** Number of items + Total price **/
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .background(color = PrimaryFill)
                        .clip(shape = RoundedCornerShape(8))
                        .padding(all = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Items ($totalItemsNumber)",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "$totalItemsPrice DA",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }
        }

        PrincipalButton(
            text = "Continue",
            onClick = {}
        )
    }
}