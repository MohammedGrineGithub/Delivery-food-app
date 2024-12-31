package com.example.deliveryfoodapp.pages

import android.provider.ContactsContract.Contacts.Photo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.utils.Routes

@Composable
fun OrderPlacedPage(navController : NavHostController) {


    Column (
        modifier = Modifier.fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

Icon(
    painter = painterResource(id=R.drawable.done),
    contentDescription = "validate order icons",
modifier = Modifier.fillMaxWidth()
    .size(80.dp)
    .align(Alignment.CenterHorizontally),
    tint = Color(0xFF058240)

)

        Spacer(modifier = Modifier.height(18.dp))



        Text(
            text = "Order confirmed !",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
            )
        )

        Spacer(modifier = Modifier.height(18.dp))


        Text(
            text = "Your order has been successfully placed    ",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row {
            Text(
                text = "click here to go to orders page",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                )

                )


            Text(
                text = "My orders page",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline,
                    color = Color(0xFF3AA838)
                ),
                modifier = Modifier.clickable {
                    navController.navigate(Routes.MY_ORDERS_PAGE)
                }
            )
        }



        Spacer(modifier = Modifier.height(102.dp))


         Button(
             onClick = {
                 navController.navigate(Routes.HOME_PAGE)
             },
             modifier = Modifier.fillMaxWidth()
                 .height(52.dp)
                 .clip(RoundedCornerShape(8.dp)),
             colors = ButtonDefaults.buttonColors(
                 containerColor = Color(0xFF058240), //bg color
                 contentColor = Color.White // clr de text
             )

         ) {
Text(
    text=("Back to Home"),
    style = TextStyle(
        fontSize = 20.sp,
    fontWeight = FontWeight.Bold
)
)

         }



    }




}