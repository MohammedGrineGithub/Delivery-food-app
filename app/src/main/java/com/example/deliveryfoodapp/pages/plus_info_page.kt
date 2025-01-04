package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.utils.Routes

@Composable
fun PlusInfoPage(navController : NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light gray background
    ) {
        Box {
            // Top Image
            Image(
                painter = painterResource(id = R.drawable.banner_photo),
                contentDescription = "Restaurant",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            // Back Arrow
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back",
                modifier = Modifier
                    .padding(24.dp)
                    .size(32.dp)
                    .align(Alignment.TopStart)
                    .clickable {}
            )
            // Bottom Circle Image
            Image(
                painter = painterResource(id = R.drawable.restaurantlogo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(70.dp)
                    //.clip(CircleShape)
                    .align(Alignment.BottomCenter)
                   // .background(Color.White) // Optional border effect
                    //.border(2.dp, Color.White, CircleShape)
                    .offset(y = 35.dp) // Adjust position below the image
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title and Address
            Text(
                text = "American Burger",
                fontWeight = FontWeight.Bold
            )
            IconText(icon = R.drawable.position_icon, text = "Rue Nadjet Slimane, Kouba, Algiers",0.dp)
            Text(
                text = "fast food",
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Details Row
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconText(icon = R.drawable.timer_icon, text = "20-25 min",0.dp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "150 DA",fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(16.dp))
                IconText(icon = R.drawable.eye_icon, text = "209",0.dp)
                Spacer(modifier = Modifier.width(16.dp))
                IconText(icon = R.drawable.star_icon, text = "4.7",0.dp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contact Information
            InfoSection(
                icon = R.drawable.phone_icon,
                title = "Phone",
               info =  { Text(text = "0661 85 47 96",fontWeight = FontWeight.Medium,modifier = Modifier.padding(start = 20.dp)) }
            )
            InfoSection(
                icon = R.drawable.mail_icon,
                title = "E-mail",
                info = { Text(text = "example@gmail.com",fontWeight = FontWeight.Medium,modifier = Modifier.padding(start = 20.dp)) }
            )

            // Social Media

            InfoSection(
                icon = R.drawable.social_icon,
                title = "Social Media",
                info = {
                    IconText(icon = R.drawable.fb_icon, text = "American Burger",20.dp)
                    IconText(icon = R.drawable.insta_icon, text = "American Burger",20.dp)
                }

            )
        }
    }
}

@Composable
fun IconText(icon: Int, text: String, marginStart: Dp ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = marginStart) // Add margin on the left
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun InfoSection(icon: Int, title: String, info : @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Primary

            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        info()
    }
}
