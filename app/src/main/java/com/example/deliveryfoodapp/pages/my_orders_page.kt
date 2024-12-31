package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.utils.*


@Composable
fun MyOrdersPage(navController : NavHostController) {
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        items(sampleOrders) { order ->
//            OrderCard(order = order)
//        }
//    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
        //verticalArrangement = Arrangement.SpaceEvenly // Space out items evenly
    ){
    Text(
        "My Orders",
        modifier = Modifier.padding(top = 20.dp),
        color = Color.Black,
        fontFamily = lemonFontFamily,
        fontSize = 24.sp
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(sampleOrders) { order ->
            OrderCard(order = order)
        }
    }
    }

}


@Composable
fun OrderCard(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // First Row: Image and Restaurant Name
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(order.imageUrl), // Replace with your image resource
                    contentDescription = "Restaurant Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = order.restaurantName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Second Row: Columns and Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Column 1: Total Price
                Column {
                    Text(
                        text = "Total price",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        color = Primary
                    )
                    Text(
                        text = order.price,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Column 2: Ordered At
                Column {
                    Text(
                        text = "Ordered at",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        color = Primary
                    )
                    Text(
                        text = order.orderedAtTime,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = order.orderedAtDate,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Button
                Button(
                    onClick = { /* Handle the click action */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = order.statusId, // Button background color
                        contentColor = Color.White // Text color inside the button
                    ),
                    shape = RoundedCornerShape(10.dp), // Rounded corners for the button
                    modifier = Modifier
                        .width(120.dp) // Set button width
                        .height(40.dp) // Set button height
                        .padding(0.dp)
                ) {
                    Text(
                        text = order.status,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
