package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.deliveryfoodapp.models.Notification
import com.example.deliveryfoodapp.models.Order
import com.example.deliveryfoodapp.models.Restaurant
import java.util.Date

@Composable
fun NotificationsPage(navController : NavHostController) {


    var notification_list = listOf(
        Notification(1,1,"Your order now is created","26/12/2024 15:30 pm"),
        Notification(1,1,"Your order now is picked up","26/12/2024 15:30 pm"),
        Notification(1,1,"Your order now is on way","26/12/2024 15:30 pm"),
        Notification(1,1,"Your order no is cancel","26/12/2024 15:30 pm"),

    )

var order1 = Order(1,1,1450.0,"26/12/2024 15:30 pm",0,"dont put olive",1)

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp)

    ) {

        //Header

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back icons",
                modifier = Modifier.size(16.dp)

            )

            Spacer(modifier = Modifier.width(78.dp))

            Column {
                Text(
                    text = "Notification",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(13.dp))

                Text(
                    text = "All notification are display here",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }


        }

        Spacer(modifier = Modifier.height(51.dp))

        LazyColumn {

            items(notification_list)
            { notification ->
                Notification_Item(notification)
                Spacer(modifier = Modifier.height(24.dp))



            }

        }



    }







}

@Composable
fun Notification_Item( notification: Notification){




}

