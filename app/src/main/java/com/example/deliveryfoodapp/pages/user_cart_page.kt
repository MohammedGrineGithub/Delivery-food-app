package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import com.example.deliveryfoodapp.R
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.PrimaryFill
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.ui.theme.SecondaryFill
import com.example.deliveryfoodapp.ui.theme.White
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.CustomAppBar
import com.example.deliveryfoodapp.widgets.PrincipalButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCartPage(navController : NavHostController){

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    /** ********************************************** **/
    // TODO ab3at restaurantID bin hado les page (men home 7ata lhna)
    val restaurantID = 1

    val userCart = remember {
        mutableStateOf(
            authenticatedUser
                .getUserCartByRestaurantID(restaurantID = restaurantID)
        )
    }

    /** ********************************************** **/

    val totalItemsNumber = remember {
        mutableIntStateOf(userCart.value.totalItems())
    }
    val totalItemsPrice = remember {
        mutableDoubleStateOf(userCart.value.totalPrice())
    }


    /** Main Page **/
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(all = 20.dp)
    ) {
        /** AppBar + Main content **/
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            /** AppBar (Title + back arrow icon) **/
            CustomAppBar(
                text = "My Cart",
                onClick = {navController.popBackStack()}
            )

            Spacer(Modifier.height(24.dp))

            /** Main content **/
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ){
                /** Number of items + Total price **/
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .clip(shape = RoundedCornerShape(8))
                        .background(color = PrimaryFill)
                        .padding(all = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Items (${totalItemsNumber.intValue})",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${totalItemsPrice.doubleValue} DA",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(24.dp))

                /** List of EditItemCard **/
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                        .height(screenHeight/2),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(userCart.value.orderItems, key = { _, item -> item.id }) { index, orderItem ->

                        var note by remember {
                            mutableStateOf(userCart.value.orderItems[index].note)
                        }
                        var itemName by remember {
                            mutableStateOf(userCart.value.orderItems[index].item.name)
                        }
                        val singleItemTotalPrice = remember {
                            mutableDoubleStateOf(
                                userCart.value.orderItems[index].totalPrice()
                            )
                        }
                        val itemQuantity = remember {
                            mutableIntStateOf(
                                userCart.value.orderItems[index].itemQuantity
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                /** Item name and button to delete it **/
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        itemName,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        "Delete item",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier.clickable {
                                            // update userCart
                                            /*val updatedOrderItems = userCart.value.orderItems.toMutableList()
                                            updatedOrderItems.removeAt(index)
                                            userCart.value = userCart.value.copy(orderItems = updatedOrderItems)
                                            */

                                            userCart.value = userCart.value.copy(
                                                orderItems = userCart.value.orderItems.toMutableList().apply {
                                                    removeAt(index)
                                                }
                                            )

                                            // update states
                                            totalItemsNumber.intValue = userCart.value.totalItems()
                                            totalItemsPrice.doubleValue = userCart.value.totalPrice()

                                            // update authenticated user with that userCart
                                            authenticatedUser.updateByUserCart(userCart = userCart.value)

                                            // TODO the userCart of the user in SqlLite with that userCart
                                        }
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {

                                    Text(
                                        text = "${singleItemTotalPrice.doubleValue} DA",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal
                                    )

                                    Spacer(Modifier.height(4.dp))

                                    Row(
                                        modifier = Modifier
                                            .width(120.dp)
                                            .clip(shape = RoundedCornerShape(8))
                                            .background(color = SecondaryFill),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        IconButton(
                                            onClick = {
                                                // update itemQuantity state variable to show
                                                itemQuantity.value += 1

                                                // update the itemQuantity of the order item of the userCart
                                                userCart.value.orderItems[index].itemQuantity += 1

                                                // update the singleItemTotalPrice state variable of the order item of the user cart
                                                singleItemTotalPrice.doubleValue = userCart.value.orderItems[index].totalPrice()

                                                // update the totalItemsNumber state variable
                                                totalItemsNumber.intValue = userCart.value.totalItems()

                                                // update the totalItemsPrice state variable
                                                totalItemsPrice.doubleValue = userCart.value.totalPrice()

                                                // update authenticated user with that userCart
                                                authenticatedUser.updateByUserCart(userCart = userCart.value)

                                                // TODO the userCart of the user in SqlLite with that userCart

                                            }
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.add_icon),
                                                contentDescription = "plus",
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }

                                        Text(
                                            text = "${itemQuantity.intValue}",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Normal
                                        )

                                        IconButton(
                                            onClick = {
                                                if (itemQuantity.intValue > 1) {

                                                    // update itemQuantity state variable to show
                                                    itemQuantity.value -= 1

                                                    // update the itemQuantity of the order item of the user cart
                                                    userCart.value.orderItems[index].itemQuantity -= 1

                                                    // update the singleItemTotalPrice state variable of the order item of the user cart
                                                    singleItemTotalPrice.doubleValue = userCart.value.orderItems[index].totalPrice()

                                                    // update the totalItemsNumber state variable
                                                    totalItemsNumber.intValue = userCart.value.totalItems()

                                                    // update the totalItemsPrice state variable
                                                    totalItemsPrice.doubleValue = userCart.value.totalPrice()

                                                    // update authenticated user with that userCart
                                                    authenticatedUser.updateByUserCart(userCart = userCart.value)

                                                    // TODO the userCart of the user in SqlLite with that userCart

                                                }
                                            }
                                        ) {
                                            Icon(
                                                painter = painterResource(R.drawable.remove_icon),
                                                contentDescription = "minus",
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(Modifier.height(16.dp))

                            OutlinedTextField(
                                onValueChange = { newNote ->
                                    note = newNote
                                    // Update the note of the item in the userCart
                                    userCart.value.orderItems[index].note = newNote
                                },
                                value = "$note",
                                modifier = Modifier
                                    .fillMaxWidth(),
                                maxLines = 4,
                                shape = RoundedCornerShape(
                                    topStart = 2.dp,
                                    topEnd = 2.dp,
                                    bottomStart = 16.dp,
                                    bottomEnd = 16.dp
                                ),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Secondary.copy(alpha = 0.4f),
                                    unfocusedBorderColor = GreyStroke
                                )
                            )
                        }

                        Spacer(Modifier.height(24.dp))

                        HorizontalDivider( color = GreyStroke)
                    }
                }

                /** Add shadow effect after the list **/
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.1f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                Spacer(Modifier.height(24.dp))

                /** Row to add new items **/
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Do you need more items ?",
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )


                    Button(
                        onClick = {navController.popBackStack()},
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.add_icon),
                                contentDescription = "Add more items",
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                text = "Add",
                                fontSize = 14.sp,
                                color = White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }


            }
        }

        /** Continue button **/
        PrincipalButton(
            text = "Continue",
            onClick = {
                // do some logic (update the userCart)
                navController.navigate(Routes.VALIDATE_PAYMENT_PAGE){
                }
            }
        )
    }
}