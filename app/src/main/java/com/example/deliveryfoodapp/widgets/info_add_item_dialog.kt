package com.example.deliveryfoodapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.models.Item
import com.example.deliveryfoodapp.models.OrderItem
import com.example.deliveryfoodapp.models.UserCart
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoAddItemDialog(userCart: UserCart, item: Item, onDismiss: () -> Unit) {

    // get the order item if exists, else create new one
    val orderItem : OrderItem = userCart.getOrderItemByItemID(item)

    var note by remember { mutableStateOf(orderItem.note ?: "") }
    val itemQuantity = remember { mutableIntStateOf(1) }
    val totalPrice = remember { mutableDoubleStateOf(item.price) }
    /** ************************************************************* **/


    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 16.dp,
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                /** Item photo and close icon **/
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    AsyncImage(
                        model = item.photo.imagePath,
                        contentDescription = "item photo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.FillBounds
                    )

                    /** Circle back icon button **/
                    Box(
                        modifier = Modifier
                            .padding(top = 12.dp, start = 12.dp)
                            .align(Alignment.TopStart)
                    ){

                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(60.dp))
                                .background(White)
                                .padding(all = 16.dp)
                        ){
                            IconButton(
                                onClick = { onDismiss() },
                                modifier = Modifier.size(44.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_icon),
                                    contentDescription = "close",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(38.dp)
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ){
                    /** Item name + price **/
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${item.price} DA / Piece",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    /** Item ingredients **/
                    Text(
                        text = "Ingredients : ${item.ingredients}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )

                    Spacer(Modifier.height(8.dp))

                    HorizontalDivider(thickness = 4.dp, color = GreyStroke.copy(alpha = 0.4f))

                    Spacer(Modifier.height(8.dp))

                    /** Text and Icon of note **/
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    ) {
                        /** Icon of note**/
                        Icon(
                            painter = painterResource(id = R.drawable.note_icon),
                            contentDescription = "note to driver",
                            Modifier.size(18.dp),
                            tint = Color.Black
                        )

                        /** Text add note **/
                        Text(
                            text = "Add note",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(2.dp))

                    /** TextField to add note to the item **/
                    OutlinedTextField(
                        onValueChange = { newNote ->
                            note = newNote
                        },
                        placeholder = { Text(
                            text = "Ex : Without onion and without hot sauce",
                            style = TextStyle( fontSize = 12.sp, fontWeight = FontWeight.Light, color = Color(0xFF9F9F9F)  )
                        ) },
                        value = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp)
                            .height(80.dp),
                        maxLines = 1,
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
                    Spacer(Modifier.height(16.dp))

                    /** Add or remove quantity **/
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Row(
                            modifier = Modifier
                                .width(140.dp)
                                .align(Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = {
                                    itemQuantity.value += 1
                                    totalPrice.doubleValue *= itemQuantity.intValue
                                },
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(60))
                                    .background(color = Primary)
                                    .size(40.dp)
                            ){
                                Icon(
                                    painter = painterResource(R.drawable.add_icon),
                                    contentDescription = "plus",
                                    modifier = Modifier.size(20.dp),
                                    tint = White
                                )
                            }
                            Text(
                                text = "${itemQuantity.intValue}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            IconButton(
                                onClick = {
                                    if (itemQuantity.intValue > 1) {
                                        totalPrice.doubleValue /= itemQuantity.intValue
                                        itemQuantity.value -= 1
                                    }
                                },
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(60))
                                    .background(color = Primary)
                                    .size(40.dp)
                            ){
                                Icon(
                                    painter = painterResource(R.drawable.remove_icon),
                                    contentDescription = "minus",
                                    modifier = Modifier.size(20.dp),
                                    tint = White
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total price",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${totalPrice.doubleValue} DA",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    /** Button to add to the cart **/
                    Button(
                        onClick = {
                            // update note
                            orderItem.note = note

                            // update quantity
                            orderItem.itemQuantity += itemQuantity.intValue

                            // update user cart
                            userCart.updateByOrderItem(orderItem)

                            // update authentication user with that userCart
                            authenticatedUser.updateByUserCart(userCart = userCart)

                            // TODO update user with that userCart in SqlLite


                            // close the dialog
                            onDismiss()
                        },
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 12.dp, start = 36.dp, end = 36.dp)
                            .fillMaxWidth()
                            .height(46.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        )
                    ) {
                        Text(
                            text = "Add to cart",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = White
                        )
                    }

                }
            }
        }
    }
}
