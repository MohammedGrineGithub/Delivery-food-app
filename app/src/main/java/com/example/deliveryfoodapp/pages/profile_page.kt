package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.ui.theme.CardBackground
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.LogoutDialog

@Composable
fun ProfilePage(navController : NavHostController) {

    val fullname by remember { mutableStateOf("Sadaoui Said Abdelwareth") }

    var showLogoutDialog by remember { mutableStateOf(false) } // État pour afficher la boîte de dialogue


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            //Profile text
            Text(
                text = "Profile",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = lemonFontFamily
                ),
                modifier = Modifier.padding(top = 20.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            //Profile image and name
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(84.dp)
                        .background(Secondary, CircleShape)
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = fullname.first().toString().uppercase(),
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = fullname,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Spacer(modifier = Modifier.height(48.dp))

            //My Account

            Column(
                Modifier.width(355.dp)
            ) {
                Text(
                    text = "My Account",
                    style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(27.dp))

                Column(
                    modifier = Modifier.clip(RoundedCornerShape(25.dp))
                        .background(CardBackground)
                        .height(266.dp)
                        .padding(vertical = 15.dp, horizontal = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    ItemProfil("person", "Personal information", navController)
                    ItemProfil("lock", "Change password", navController)
                    ItemProfil("maps", "Change location", navController)

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .clickable { showLogoutDialog = true    } ,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {

                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                painter = painterResource(R.drawable.logout),
                                contentDescription = "icon",
                                Modifier.size(28.dp),
                                tint = Red
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Log out",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Red
                                )
                            )

                        }

                        Icon(
                            painter = painterResource(R.drawable.arrow),
                            contentDescription = "arrow",
                            Modifier.size(28.dp),
                            tint = Red
                        )

                    }


                }
            }

            Spacer(modifier = Modifier.height(150.dp))

            Text(
                text = "MAKLAEXPRESS . Version 1.0",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraLight
                )
            )


        }


        if (showLogoutDialog) {
            LogoutDialog(
                onDismiss = { showLogoutDialog = false }, // Fermer la boîte de dialogue
                onConfirm = {
                    // Action de déconnexion
                    showLogoutDialog = false
                    // Ajoutez ici la logique pour déconnecter l'utilisateur
                }
            )
        }


    }
}

@Composable
fun ItemProfil(icon:String,name:String,navController: NavHostController) {
    val iconId = when(icon)
    {
        "person" -> R.drawable.person
         "lock"   ->R.drawable.lock
         "maps"   ->R.drawable.maps
         else -> R.drawable.logout

    }

    val navigate = when (icon)
    {
        "person" -> Routes.MY_INFORMATION_PAGE
        "lock"   ->Routes.CHANGE_PASSWORD_PAGE
        else  -> Routes.CHANGE_LOCATION_PAGE

    }

    val color = when (icon)
    {
        "logout" -> Red
        else -> Color.Black
    }

    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { navController.navigate(navigate)  } ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(iconId),
                contentDescription = "icon",
                Modifier.size(28.dp),
                tint = color
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            )

        }

        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = "arrow",
            Modifier.size(28.dp),
            tint = color
        )

    }
}