package com.example.deliveryfoodapp.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.backend_services.user_api.UserEndpoints
import com.example.deliveryfoodapp.currentOrderID
import com.example.deliveryfoodapp.currentRestaurant
import com.example.deliveryfoodapp.models.OrderItem
import com.example.deliveryfoodapp.models.UserOrder
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.utils.OrderStatuses
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.CustomAppBar
import com.example.deliveryfoodapp.widgets.PrincipalButton

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MyOrderDetailsPage(navController: NavHostController) {

    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(true) }
    val scrollState = rememberLazyListState()

    val order = remember {
        mutableStateOf(UserOrder.emptyUserOrder())
    }

    val deliveryFees = remember { mutableIntStateOf(0) }
    val serviceFees = 0.0
    val totalPrice = remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(Unit) {
        try {
            order.value = UserEndpoints.fetchOrderDetailsByOrderID(currentOrderID)
            deliveryFees.intValue = order.value.restaurant.deliveryPrice
            totalPrice.doubleValue = order.value.itemsTotalPrice + deliveryFees.intValue + serviceFees
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            isLoading.value = false
        }
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }else {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(20.dp)
        ) {
            /** App bar **/
            CustomAppBar(
                text = "",
                onClick = {navController.popBackStack()}
            )
            Spacer(Modifier.height(24.dp))
            /** Content **/
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = scrollState

            ) {

                //Body
                item {


                    //Restaurant
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.restaurant_icon),
                            contentDescription = "restaurant_icon",
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Restaurant",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }


                    Spacer(modifier = Modifier.height(16.dp))
                    //Restaurant information
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = order.value.restaurant.logo.imagePath,
                            contentDescription = "restaurant logo",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(90.dp))
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = order.value.restaurant.restaurantName,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))


                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.position_icon),
                                    contentDescription = "position",
                                    modifier = Modifier.size(20.dp),
                                    tint = Red
                                )
                                Spacer(modifier = Modifier.width(6.dp))


                                Text(
                                    text = order.value.restaurant.location.address,
                                    style = TextStyle(
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                )

                            }

                        }
                    }

                    Line(24,24)


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
                                painter = painterResource(id = R.drawable.order_icon),
                                contentDescription = "order icon",
                                modifier = Modifier.size(26.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Order",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(8),
                                    color = OrderStatuses.getStatusColor(order.value.status)
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Row(
                                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                                    .widthIn(min = 100.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.order_status_icon),
                                    contentDescription = "order status",
                                    modifier = Modifier.size(24.dp),
                                    tint = OrderStatuses.getStatusColor(order.value.status)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = OrderStatuses.getStatusValue(order.value.status),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = OrderStatuses.getStatusColor(order.value.status)
                                    )
                                )
                            }

                        }

                    }

                    Spacer(modifier = Modifier.height(30.dp))

                }

                //Order item list
                itemsIndexed(order.value.orderItems) { _, orderItem ->
                    Order_Item(orderItem)
                }

                item {
                    Line(0,30)

                    //Billing
                    BillingComponent(order.value.itemsTotalPrice, deliveryFees.intValue, serviceFees, totalPrice.doubleValue)


                    Line(30,30)


                    // NOTE TO DRIVER
                    if (!order.value.deliveryNote.isNullOrEmpty()) {
                        Column(modifier = Modifier.fillMaxWidth())
                        {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.note_icon),
                                    contentDescription = "note to driver",
                                    Modifier.size(26.dp),
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
                                    text = "${order.value.deliveryNote}",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                )


                            }
                            Line(30,30)
                        }
                    }


                    //DRIVER INFORMATION
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically


                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.moto_icon),
                                contentDescription = "billing",
                                Modifier.size(26.dp),
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
                        Text(
                            text = "${order.value.deliveryPerson.fullName}    ${order.value.deliveryPerson.phone}",
                            style = TextStyle(
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )


                    }


                }
                //Button
                item {
                    if (order.value.status == OrderStatuses.DELIVERED_STATUS){
                        Spacer(modifier = Modifier.height(50.dp))
                        PrincipalButton(
                            text = "Rate the order",
                            onClick = {
                                navController.navigate(Routes.RATING_PAGE)
                                currentRestaurant.id = order.value.restaurant.id
                            }
                        )
                    }

                }


            }
        }
    }


}

@SuppressLint("DefaultLocale")
@Composable
fun Order_Item(orderItem: OrderItem) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(
                    text = orderItem.item.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
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
                text = "${String.format("%.1f",orderItem.item.price)} DA",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
            )
        }
        if (!orderItem.note.isNullOrEmpty()){
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

    }

    Spacer(modifier = Modifier.height(30.dp))

}


@SuppressLint("DefaultLocale")
@Composable
fun BillingComponent(
    totalItemsPrice: Double,
    deliveryFees: Int,
    serviceFees: Double,
    totalPrice: Double,
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically


        ) {
            Icon(
                painter = painterResource(id = R.drawable.billing_icon),
                contentDescription = "billing",
                Modifier.size(26.dp),
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
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = "${String.format("%.1f",totalItemsPrice)} DA",
                    style = TextStyle(
                        fontSize = 13.sp,
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
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = "$deliveryFees DA",
                    style = TextStyle(
                        fontSize = 13.sp,
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
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = "$serviceFees DA",
                    style = TextStyle(
                        fontSize = 13.sp,
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
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF058240)
                    )
                )

                Text(
                    text = "${String.format("%.1f",totalPrice)} DA",
                    style = TextStyle(
                        fontSize = 16.sp,
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

    HorizontalDivider(color = GreyStroke)

    Spacer(modifier = Modifier.height(h2.dp))
}