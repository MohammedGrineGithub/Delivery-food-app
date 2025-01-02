package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.models.AppImage
import com.example.deliveryfoodapp.models.DeliveryPerson
import com.example.deliveryfoodapp.models.Item
import com.example.deliveryfoodapp.models.Order
import com.example.deliveryfoodapp.models.OrderItem
import com.example.deliveryfoodapp.models.Restaurant
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.PrincipalButton
import okhttp3.Route

@Composable
fun MyOrderDetailsPage(navController: NavHostController) {

    //***// 1.i need the Order_id  as parametre
    var order_id by remember { mutableStateOf(1) }

    // recuper Order et le mettre dans Order --> fonction return order from id_order
    val order by remember {
        mutableStateOf(
            Order(
                order_id,
                1,
                1,
                1250.00,
                "12/12/2024",
                0,
                "i'm in Hydra ",
                1
            )
        )
    }

    //***// 2.i need fonction return information de restaurant from the order.restaurantId i cant refer to Restaurant model
    //var restaurant = Restaurant(1,"American Burger", location = "Rue Nadjet Slimane, Kouba, Algiers")

    var restaurantName by remember { mutableStateOf("American Burger") }
    var logo by remember { mutableStateOf("American Burger") }
    var location by remember { mutableStateOf("Rue Nadjet Slimane, Kouba, Algiers") }


    //***// 3.fonction return List of  order item apartir de  order_id
    var photoItem by remember { mutableStateOf(AppImage(1, "photoImage")) }
    var item by remember {
        mutableStateOf(
            Item(
                1,
                "Omelette aux 3 fromage",
                "cheez,union,sauce",
                600.00,
                photoItem
            )
        )
    }
    var list_orderItem = listOf(
        OrderItem(1, item, "Without onion and without hot sauce", 5),
        OrderItem(1, item, "Without onion and without hot sauce", 5),
        OrderItem(1, item, "Without onion and without hot sauce", 5),
        OrderItem(1, item, "Without onion and without hot sauce", 5),
        OrderItem(1, item, "Without onion and without hot sauce", 5),
        OrderItem(1, item, "Without onion and without hot sauce", 5),

    )


    // Billing variable
    var Delivery_fees by remember { mutableStateOf(250.00) }
    var Service_fees by remember { mutableStateOf(0.00) }
    var Total_price by remember { mutableStateOf(order.totalPrice + Delivery_fees + Service_fees) }


    // fetch driver information from "deliveryPersonId" -->fonction recupere delivryPreson
    var deliveryPerson by remember {
        mutableStateOf(
            DeliveryPerson(
                1,
                "Megdad Imed",
                "05555555555"
            )
        )
    }


    var statusArray = arrayOf("Waiting", "Prepared", "Picked Up", "On Way", "Delivered", "Canceled")

    val scrollState = rememberLazyListState() // État du défilement


    LazyColumn(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        state = scrollState

    ) {

        //Head
        item {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back icons",
                modifier = Modifier.size(16.dp)

            )
            Spacer(modifier = Modifier.height(19.dp))
        }

        //Body
        item {


            //Restaurant
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.restaurant),
                    contentDescription = "sasa",
                    modifier = Modifier.size(width = 20.dp, height = 18.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "Restaurant",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

            }


            Spacer(modifier = Modifier.height(20.dp))
            //Restaurant information
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "sasa",
                    modifier = Modifier
                        .size(width = 57.dp, height = 57.dp)
                        .clip(RoundedCornerShape(90.dp))
                )
                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = restaurantName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.maps),
                            contentDescription = "sasa",
                            modifier = Modifier.size(width = 12.dp, height = 15.dp),
                            tint = Red
                        )
                        Spacer(modifier = Modifier.width(6.dp))


                        Text(
                            text = location,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Light
                            )
                        )

                    }

                }
            }

            Line(30,30)


            //ORDER and status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.order),
                        contentDescription = "Line",
                        modifier = Modifier.size(width = 20.dp, height = 18.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Order",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .size(width = 152.dp, height = 43.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(8),
                            color = Primary
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.statusorder),
                        contentDescription = "Line",
                        modifier = Modifier.size(width = 20.dp, height = 20.dp),
                        tint = Primary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = statusArray[order.status],
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Primary
                        )
                    )


                }

            }

            Spacer(modifier = Modifier.height(30.dp))

        }

        //Order item list
        items(list_orderItem)
        { orderItem ->
            Order_Item(orderItem)
        }

        item {
            Line(0,30)

            //Billing
            BillingComponent(order.totalPrice, Delivery_fees, Service_fees, Total_price)


            Line(30,30)


            // NOTE TO DRIVER
            Column(modifier = Modifier.fillMaxWidth())
            {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.note),
                        contentDescription = "note to driver",
                        Modifier.size(27.38.dp),
                        tint = Color.Black
                    )


                    Spacer(modifier = Modifier.width(12.dp))


                    Text(
                        text = "Note to driver",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .size(width = 388.dp, height = 39.dp)
                        .border(
                            width = 0.5.dp,
                            shape = RoundedCornerShape(
                                topEnd = 0.dp,
                                topStart = 0.dp,
                                bottomEnd = 25.dp,
                                bottomStart = 25.dp
                            ),
                            color = BlackStroke
                        )
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Note :",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))

                    Text(
                        text = "${order.deliveryNote}",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )


                }


            }

            Line(30,30)


            //DRIVER INFORMATION
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically


                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.driver),
                        contentDescription = "billing",
                        Modifier.size(27.38.dp),
                        tint = Color.Black
                    )


                    Spacer(modifier = Modifier.width(12.dp))


                    Text(
                        text = "Driver information",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))



                Row(

                    modifier = Modifier.width(174.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${deliveryPerson.fullName}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        text = "${deliveryPerson.phone}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )


                }


            }


        }
        //Button
        item {
            Spacer(modifier = Modifier.height(53.dp))

            PrincipalButton("Rate the order", { navController.navigate(Routes.RATING_PAGE) })

        }


    }


}

@Composable
fun Order_Item(orderItem: OrderItem) {
    // item : reelement apartir de itemid recuperer item , item vas etre fetchée dans le composable Order_Item


    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = "${orderItem.item.name}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.width(13.dp))
                Text(
                    text = "(${orderItem.itemQuantity})",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                )
            }
            Text(
                text = "${orderItem.item.price.toInt()} DA",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .size(width = 388.dp, height = 39.dp)
                .border(
                    width = 0.5.dp,
                    shape = RoundedCornerShape(
                        topEnd = 0.dp,
                        topStart = 0.dp,
                        bottomEnd = 25.dp,
                        bottomStart = 25.dp
                    ),
                    color = BlackStroke
                )
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Note :",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = "${orderItem.note}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )


        }

    }

    Spacer(modifier = Modifier.height(30.dp))

}


@Composable
fun BillingComponent(
    totalPrice: Double,
    Delivery_fees: Double,
    Service_fees: Double,
    Total_price: Double
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically


        ) {
            Icon(
                painter = painterResource(id = R.drawable.billing),
                contentDescription = "billing",
                Modifier.size(27.38.dp),
                tint = Color.Black
            )


            Spacer(modifier = Modifier.width(12.dp))


            Text(
                text = "Billing",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Items total",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = "${totalPrice.toInt()} DA",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Delivery fees",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = "${Delivery_fees.toInt()} DA",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Service fees",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = "${Service_fees.toInt()} DA",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total price",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF058240)
                    )
                )

                Text(
                    text = "${Total_price.toInt()} DA",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF058240)
                    )
                )

            }


        }


    }


}


@Composable
fun Line(h1:Int,h2:Int){
    Spacer(modifier = Modifier.height(h1.dp))

    Icon(
        painter = painterResource(id = R.drawable.line),
        contentDescription = "Line",
        modifier = Modifier.size(width = 399.dp, height = 1.dp)
    )

    Spacer(modifier = Modifier.height(h2.dp))
}
