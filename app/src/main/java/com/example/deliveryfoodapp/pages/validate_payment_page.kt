package com.example.deliveryfoodapp.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.currentRestaurant
import com.example.deliveryfoodapp.models.UserCart
import com.example.deliveryfoodapp.services.repositories.UserCartRepository
import com.example.deliveryfoodapp.services.room.RoomUserCart
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.CustomAppBar
import com.example.deliveryfoodapp.widgets.PrincipalButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidatePaymentPage(navController : NavHostController) {

    var userCart = authenticatedUser.getUserCartByRestaurantID(restaurantID = currentRestaurant.id)

    val address by remember {
        mutableStateOf(currentRestaurant.location.address)
    }
    val phone by remember {
        mutableStateOf(authenticatedUser.phone)
    }
    var note_To_driver by remember {
        mutableStateOf("")
    }
    val Items_total by remember {
        mutableDoubleStateOf(
            userCart.totalPrice()
        )
    }
    val Delivery_fees by remember {
        mutableIntStateOf(currentRestaurant.deliveryPrice)
    }
    val Service_fees = 0

    val Total_price by remember { mutableDoubleStateOf(Items_total + Delivery_fees + Service_fees) }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        Column {
            CustomAppBar(
                text = "Validate payment",
                onClick = {navController.popBackStack()}
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .clickable { navController.navigate(Routes.CHANGE_LOCATION_PAGE) },
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.address_icon),
                            contentDescription = "address",
                            Modifier.size(26.dp),
                            tint = Color.Black
                        )



                        Spacer(modifier = Modifier.width(12.dp))


                        Column {
                            Text(
                                text = "Address",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = address,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light
                                )
                            )


                        }
                    }
                    Icon(
                        painter = painterResource(id=R.drawable.chevron_right_icon),
                        contentDescription = "arrow",
                        Modifier.size(22.dp)
                            .align(Alignment.CenterVertically)
                    )






                }

                Row (
                    modifier = Modifier.fillMaxWidth()
                        .clickable {  },
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {

                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.phone_icon),
                            contentDescription = "call",
                            Modifier.size(22.dp),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Phone number",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = phone,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light
                                )
                            )


                        }

                    }


                    Icon(
                        painter = painterResource(id=R.drawable.chevron_right_icon),
                        contentDescription = "arrow chevron",
                        Modifier.size(22.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                Column {

                    Row (
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

                    Spacer(modifier = Modifier.height(6.dp))

                    OutlinedTextField(
                        value = note_To_driver,
                        onValueChange = {note_To_driver=it},
                        placeholder = { Text(
                            text = "Ex : I live in the third stage of the building",
                            style = TextStyle( fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color(0xFF9F9F9F)  )) },
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 16.dp,
                            bottomStart = 16.dp
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Secondary.copy(alpha = 0.4f),
                            unfocusedBorderColor = GreyStroke
                        )


                    )

                }

                Column {
                    Row (
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
                                text ="Items total",
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )

                            Text(
                                text = "$Items_total DA",
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
                                text ="Delivery fees",
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )

                            Text(
                                text = "$Delivery_fees DA",
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
                                text ="Service fees",
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )

                            Text(
                                text = "$Service_fees DA",
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
                                text ="Total price",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF058240)
                                )
                            )

                            Text(
                                text = "$Total_price DA",
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
        }

        PrincipalButton(
            text = "Place the order",
            onClick = {

                // TODO Create new order fel backend

                // Delete that cart from the authenticatedUser
                authenticatedUser.deleteCart(userCart)

                // delete that cart from SqlLite (their orderItems will be deleted automatically)
                val roomUserCart : RoomUserCart? = UserCartRepository.getUserCartById(userCart.id)
                if (roomUserCart != null) {
                    UserCartRepository.removeUserCart(roomUserCart)
                }
                // Empty userCart variable
                userCart = UserCart.emptyUserCart()


                navController.navigate(Routes.ORDER_PLACED_PAGE) {
                    popUpTo(0) { inclusive = true } // Clear the entire back stack
                }
            }
        )
    }
}