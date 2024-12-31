package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.utils.Routes

@Composable
fun ValidatePaymentPage(navController : NavHostController) {


    val address by remember { mutableStateOf("P549+9JQ Bab Ezzouar, Algeria") }
    val phone by remember { mutableStateOf("0745458965") }
    var note_To_driver by remember { mutableStateOf("") } // Valeur par défaut vide
    var Items_total by remember { mutableStateOf(1200) }
    var Delivery_fees by remember { mutableStateOf(250) }
    var Service_fees by remember { mutableStateOf(0) }
    var Total_price by remember { mutableStateOf(Items_total + Delivery_fees + Service_fees) }

Column(
modifier = Modifier.fillMaxSize()
    .padding(20.dp)

) {

                //Header

    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){

        Icon(
            painter = painterResource(id= R.drawable.back),
            contentDescription = "back icons",
            modifier = Modifier.size(16.dp)

        )

        Spacer(modifier = Modifier.width(78.dp))

        Text(
            text = "Validate payment",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }




    Spacer(modifier = Modifier.height(60.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Row {
                Icon(
                    painter = painterResource(id = R.drawable.adress),
                    contentDescription = "adress",
                    Modifier.size(27.38.dp),
                    tint = Color.Black
                )



                Spacer(modifier = Modifier.width(12.dp))


                Column {
                    Text(
                        text = "Adress",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = address,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    )


                }
            }




    Icon(
        painter = painterResource(id=R.drawable.icon_arrow),
        contentDescription = "arrow",
        Modifier.size(8.dp,12.dp)
            .align(Alignment.CenterVertically)
            .clickable {                 navController.navigate(Routes.CHANGE_LOCATION_PAGE) }
    )






        }



        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {

            Row {
                Icon(
                    painter = painterResource(id = R.drawable.call),
                    contentDescription = "call",
                    Modifier.size(27.38.dp),
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

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = phone!!,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    )


                }

            }


    Icon(
        painter = painterResource(id=R.drawable.icon_arrow),
        contentDescription = "arrow",
        Modifier.size(8.dp,12.dp)
            .align(Alignment.CenterVertically)
            .clickable {         navController.navigate(Routes.CHANGE_PASSWORD_PAGE) }
    )






        }


        Column {

            Row (
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

            OutlinedTextField(
                value = note_To_driver,
                onValueChange = {note_To_driver=it},
                placeholder = { Text(
                    text = "Ex : Without onion and without hot sauce",
                    style = TextStyle( fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color(0xFF9F9F9F)  )) },
                modifier = Modifier.padding(10.dp,8.dp)
                    .height(120.dp)
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomEnd = 25.dp,
                            bottomStart = 25.dp
                        )


            )






        }


        Column {
            Row (
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
                        text ="Items total",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Text(
                        text = "$Items_total DA",
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
                        text ="Delivery fees",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Text(
                        text = "$Delivery_fees DA",
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
                        text ="Service fees",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Text(
                        text = "$Service_fees DA",
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
                        text ="Total price",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF058240)
                        )
                    )

                    Text(
                        text = "$Total_price DA",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF058240)
                        )
                    )

                }







            }



        }



        Button(
            onClick = {
                navController.navigate(Routes.ORDER_PLACED_PAGE)
            },
            modifier = Modifier.fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF058240),
                contentColor =Color.White
            )

        ) {

            Text(
                text = "Place the order",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )

            )

        }










    }




}




}

@Composable
fun Px_tp_DP(pixel:Int ):Dp{
    return (pixel/LocalDensity.current.density).dp
}

@Composable

fun pxToSp(px: Int): TextUnit {
    return (px / LocalDensity.current.density).sp
}