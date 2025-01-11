package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.currentRestaurant
import com.example.deliveryfoodapp.models.SocialMediaLink
import com.example.deliveryfoodapp.ui.theme.Blue
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Orange
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.ui.theme.White
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.utils.createRestaurantForTest
import java.time.LocalTime

@Composable
fun PlusInfoPage(navController : NavHostController) {

    fun isWithinOperatingHours(openingTime: LocalTime, closingTime: LocalTime): Boolean {
        return true
        val currentTime = LocalTime.now()
        return if (closingTime.isAfter(openingTime) || closingTime == openingTime) {
            currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)
        } else {
            currentTime.isAfter(openingTime) || currentTime.isBefore(closingTime)
        }
    }
    // TODO : Get socialMediaLinks from backend
    var socialMediaLinks : MutableList<SocialMediaLink> = mutableListOf()
    socialMediaLinks.add(
        SocialMediaLink(id = 1, name = "Facebook", url = "https://Amerrican_Burger.facebook.com")
    )

    // TODO : delete it later
    currentRestaurant = createRestaurantForTest(1)

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val feedbackList = listOf(
        "I like it very much",
        "they don't have pizza, but they are the best",
        "It's always the best place",
        "I like it very much",
        "they don't have pizza, but they are the best",
        "It's always the best place",
        "I like it very much",
        "they don't have pizza, but they are the best",
        "It's always the best place"
    )


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        /** Banner photo with currentRestaurant logo **/
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            /** Banner **/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 4 + 38.dp)
            ) {
                AsyncImage(
                    model = currentRestaurant.banner.imagePath,
                    contentDescription = "banner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 4)
                        .then(
                            if (!isWithinOperatingHours(currentRestaurant.openingTime, currentRestaurant.closingTime)) Modifier.blur(16.dp) else Modifier
                        ),
                    contentScale = ContentScale.FillBounds
                )

                // Circle back icon button
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp, start = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .background(White)
                            .padding(all = 16.dp)
                    ) {
                        IconButton(
                            onClick = { navController.navigateUp() },
                            modifier = Modifier.size(44.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.back),
                                contentDescription = "back",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Overlay with "Closed" text if not within operating hours
                if (!isWithinOperatingHours(currentRestaurant.openingTime, currentRestaurant.closingTime)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 4)
                            .background(Color.Black.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Closed ):",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }


            /** Logo **/
            AsyncImage(
                model = currentRestaurant.logo.imagePath,
                contentDescription = "logo",
                modifier = Modifier
                    .size(88.dp)
                    .align(Alignment.BottomCenter)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.FillBounds
            )
        }

        /** Restaurant details with text of plus info **/
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 20.dp, end = 20.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            /** Restaurant name **/
            Text(
                text = currentRestaurant.restaurantName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            /** Restaurant location with location icon **/
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    painter = painterResource(R.drawable.position_icon),
                    contentDescription = "position",
                    modifier = Modifier.size(18.dp),
                    tint = Red
                )

                Spacer(Modifier.width(2.dp))

                Text(
                    text = currentRestaurant.location.address,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                )
            }

            /** delivery time + price + reviewers + rating **/
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ){
                /** delivery time + icon **/
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.timer_icon),
                        contentDescription = "time",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(2.dp))

                    Text(
                        text = "${currentRestaurant.deliveryDuration} - ${currentRestaurant.deliveryDuration + 5} min",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                /** price **/
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(R.drawable.moto_icon),
                        contentDescription = "delivery",
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(Modifier.width(2.dp))

                    Text(
                        text = "${currentRestaurant.deliveryPrice} DA",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                /** reviewers + icon **/
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(R.drawable.eye_icon),
                        contentDescription = "reviewers",
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(Modifier.width(2.dp))

                    Text(
                        text = currentRestaurant.rating.reviewersCount.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                /** rating + icon **/
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rating),
                        contentDescription = "rating",
                        modifier = Modifier.size(18.dp),
                        tint = Orange
                    )

                    Spacer(Modifier.width(2.dp))

                    Text(
                        text = currentRestaurant.rating.rating.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 20.dp, end = 20.dp, bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Contact Information
            InfoSection(
                icon = R.drawable.phone_icon,
                title = "Phone",
                info =  { Text(
                    text = "${currentRestaurant.phone}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 20.dp)
                ) }
            )
            InfoSection(
                icon = R.drawable.mail_icon,
                title = "E-mail",
                info = { Text(text = "${currentRestaurant.email}",
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 20.dp)
                ) }
            )

            // Social Media

            InfoSection(
                icon = R.drawable.social_icon,
                title = "Social Media",
                info = {
                    Column(modifier = Modifier.padding(start = 20.dp)) {
                        socialMediaLinks.forEach { link ->
                            Column (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start
                            ){
                                Text(
                                    text = "${link.name} : ",
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                )
                                Text(
                                    text = link.url,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Blue,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }
                        }
                    }
                }

            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    InfoSection(
                        icon = R.drawable.feedback_icon,
                        title = "Restaurant feedbacks",
                        info = {
                            Column(
                                modifier = Modifier.padding(start = 20.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                feedbackList.forEach { infoText ->

                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.spacedBy(1.dp)
                                    ){
                                        Text(
                                            text = infoText,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                        HorizontalDivider(color = GreyStroke.copy(alpha = 0.5f))
                                    }

                                }
                            }
                        }
                    )
                }
            }
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
