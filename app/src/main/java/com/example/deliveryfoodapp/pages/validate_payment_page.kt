package com.example.deliveryfoodapp.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.backend_services.user_api.UserEndpoints
import com.example.deliveryfoodapp.currentRestaurant
import com.example.deliveryfoodapp.models.UserCart
import com.example.deliveryfoodapp.local_storage_services.repositories.UserCartRepository
import com.example.deliveryfoodapp.local_storage_services.room.RoomUserCart
import com.example.deliveryfoodapp.models.DeliveryPerson
import com.example.deliveryfoodapp.models.UserOrder
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.utils.OrderStatuses
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.ConfirmPhoneNumberPopUp
import com.example.deliveryfoodapp.widgets.CustomAppBar
import com.example.deliveryfoodapp.widgets.PrincipalButton

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidatePaymentPage(navController : NavHostController) {

    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val updateTrigger = remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }

    var userCart = authenticatedUser.getUserCartByRestaurantID(restaurantID = currentRestaurant.id)

    val address = remember {
        mutableStateOf(authenticatedUser.location.address)
    }
    val phone = remember {
        mutableStateOf(authenticatedUser.phone)
    }
    var noteToDriver by remember {
        mutableStateOf("")
    }
    val itemsTotal by remember {
        mutableDoubleStateOf(
            userCart.totalPrice()
        )
    }
    val deliveryFees by remember {
        mutableIntStateOf(currentRestaurant.deliveryPrice)
    }
    val serviceFees = 0

    val totalPrice by remember { mutableDoubleStateOf(itemsTotal + deliveryFees + serviceFees) }


    LaunchedEffect(updateTrigger.value) {
        if (updateTrigger.value) {
            try {
                // Create the order
                val userOrder = UserOrder(
                    id = 0,
                    restaurant = currentRestaurant,
                    createdAt = "",
                    status = OrderStatuses.IS_WAITING_STATUS,
                    deliveryNote = noteToDriver,
                    itemsTotalPrice = itemsTotal,
                    deliveryPerson = DeliveryPerson.emptyDeliveryPerson(),
                    orderItems = userCart.orderItems
                )
                // Save it in backend
                UserEndpoints.createOrder(
                    userID = authenticatedUser.id,
                    userOrder = userOrder
                )
                // Update user phone number
                UserEndpoints.updateUserPhoneNumber(
                    id = authenticatedUser.id,
                    phone = authenticatedUser.phone
                )
                // Toast.makeText(context, "Update phone with success", Toast.LENGTH_LONG).show()
                // Delete that cart from the authenticatedUser
                authenticatedUser.deleteCart(userCart)

                // delete that cart from SqlLite (their orderItems will be deleted automatically)
                val roomUserCart : RoomUserCart? = UserCartRepository.getUserCartById(userCart.id)
                if (roomUserCart != null) {
                    UserCartRepository.removeUserCart(roomUserCart)
                }
                // Empty userCart variable
                userCart = UserCart.emptyUserCart()

            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            } finally {
                isLoading.value = false
                updateTrigger.value = false
                navController.navigate(Routes.ORDER_PLACED_PAGE) {
                    popUpTo(0) { inclusive = true } // Clear the entire back stack
                }
            }
        }
    }

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
                                text = address.value,
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
                        .clickable {
                            showDialog = true
                        },
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
                                text = phone.value,
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
                        value = noteToDriver,
                        onValueChange = {noteToDriver=it},
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
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true


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
                                text = "${String.format("%.1f",itemsTotal)} DA",
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
                                text ="Service fees",
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
                                text ="Total price",
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
        }

        if (isLoading.value) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }else {
            PrincipalButton(
                text = "Place the order",
                onClick = {
                    isLoading.value = true
                    updateTrigger.value = true
                }
            )
        }
    }
    if (showDialog){
        ConfirmPhoneNumberPopUp(
            phoneNumber = phone.value,
            onDismiss = { showDialog = false },
            onConfirm = { updatedPhoneNumber ->
                phone.value = updatedPhoneNumber
                authenticatedUser.phone = updatedPhoneNumber
                showDialog = false
            }
        )
    }
}