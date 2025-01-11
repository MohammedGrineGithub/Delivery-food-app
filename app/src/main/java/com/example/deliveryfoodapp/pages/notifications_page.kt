package com.example.deliveryfoodapp.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.models.Notification
import com.example.deliveryfoodapp.models.Order
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.widgets.CustomAppBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun NotificationsPage(navController: NavHostController) {
    //user id AS parametre send it from "My order details page"
    var user_id by remember { mutableStateOf(1) }
    //order id AS parametre send it from "My order details page"
    var order_id by remember { mutableStateOf(1) }
    //restaurant id AS parametre send it from "My order details page"
    var restaurant_id by remember { mutableStateOf(1) }
    //restaurant name return with special Query with  restaurant_id
    var restaurant_name by remember { mutableStateOf("American Burger") }


    //notification list from the Notification_page_EndPoint( content message and localeDate ) //the message of notification will be update it with status fields from the Order models
    // notification list will be return it with user_id and order_id
    var notification_list = listOf(
        Notification(1, "r",  "Your order now is waiting", LocalDateTime.of(2024, 12, 26, 15, 30)),
        Notification(1, "1", "Your order now is picked up", LocalDateTime.of(2024, 12, 26, 15, 30)),
        Notification(1, "1",  "Your order now is on way", LocalDateTime.of(2024, 12, 26, 6, 30)),
        Notification(1, "1", "Your order no is canceled", LocalDateTime.of(2024, 12, 26, 15, 30))
        )

    var order1 = Order(1, 1, 1, 1450.0, "26/12/2024 15:30 pm", 0, "dont put olive", 1)

    val scrollState = rememberLazyListState() // État du défilement


    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp)
    ){
        /** App bar **/
        CustomAppBar(
            text = "Notifications",
            onClick = {navController.popBackStack()}
        )

        Spacer(modifier = Modifier.height(12.dp))
        /** Text All notifications displayed here **/
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "All notification are display here",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light
                )
            )
        }

        /** List of notifications **/
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = scrollState

        ) {

            //Header
            item {
                Spacer(modifier = Modifier.height(51.dp))

                //list notification


            }
            items(notification_list)
            { notification ->
                Notification_Item(notification, restaurant_name)


            }




        }
    }


}


@SuppressLint("NewApi")
@Composable
fun Notification_Item(notification: Notification, restaurant_name: String) {


    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")

    val dateTime = notification.createdAt
    val formattedDate = dateTime.format(dateFormatter)
    val formattedTime = dateTime.format(timeFormatter)
        .replace("a.m.", "am") // Remplacer "a.m." par "am"
        .replace("p.m.", "pm") // Remplacer "p.m." par "pm"


    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ) {
            Column {
                Row {

                    Text(
                        text = "Order from",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = restaurant_name,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Primary

                        )
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                NotificationMessage(notification.message)


            }

            Column {
                Text(
                    text = formattedDate,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = formattedTime,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }


        }

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider(color = GreyStroke)
        Spacer(modifier = Modifier.height(24.dp))

    }


}


@Composable

fun NotificationMessage(message: String) {
    // Liste de tous les statuts possibles
    val statusList = listOf("waiting", "prepared", "picked up", "on way", "delivered", "canceled")

    // Diviser le message en parties en fonction des statuts
    val parts = message.split(" ")
    var i = 0

    Row {
        while (i < parts.size) {
            // Vérifier si la combinaison de deux mots correspond à un statut
            if (i + 1 < parts.size && statusList.contains("${parts[i]} ${parts[i + 1]}".lowercase())) {
                val status = "${parts[i]} ${parts[i + 1]}"
                // Afficher le statut en vert ou en rouge selon le statut
                Text(
                    text = "$status ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (status.lowercase() == "canceled") Red else Primary
                    )
                )
                i += 2 // Passer aux deux mots suivants
            } else if (statusList.contains(parts[i].lowercase())) {
                // Afficher le statut en vert ou en rouge selon le statut
                Text(
                    text = "${parts[i]} ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (parts[i].lowercase() == "canceled") Red else Primary
                    )
                )
                i += 1 // Passer au mot suivant
            } else {
                // Afficher le texte normal en noir
                Text(
                    text = "${parts[i]} ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                )
                i += 1 // Passer au mot suivant
            }
        }
    }
}