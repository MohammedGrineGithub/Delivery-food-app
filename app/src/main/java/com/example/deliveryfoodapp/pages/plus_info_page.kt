package com.example.deliveryfoodapp.pages

import Location
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.models.AppImage
import com.example.deliveryfoodapp.models.CuisineType
import com.example.deliveryfoodapp.models.DeliveryPerson
import com.example.deliveryfoodapp.models.Rating
import com.example.deliveryfoodapp.models.Restaurant
import com.example.deliveryfoodapp.models.RestaurantMenu
import com.example.deliveryfoodapp.models.SocialMediaLink
import com.example.deliveryfoodapp.ui.theme.Primary
import java.time.LocalTime

@Composable
fun PlusInfoPage(navController : NavHostController) {

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

    val restaurant = Restaurant(
        id = 1,
        restaurantName = "American Burger",
        logo = AppImage(id = 1 ,imagePath = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyPuQ1_d2pRT9JOC1_6CbRZoSyeXJGijM7rA&s"),
        banner = AppImage(id = 2 ,imagePath = "https://media-cdn.tripadvisor.com/media/photo-p/2b/75/91/18/american-burger-the-best.jpg"),
        location = Location(
            id = 1,
            address = "Rue Nadjet Slimane, Kouba, Algiers",
            wilayaId = 16,
            latitude = 36.752887,
            longitude = 3.042048,
        ),
        cuisineType = CuisineType(
            id = 1,
            name = "fast food"
        ),
        rating = Rating(
            id = 1,
            reviewersCount = 209,
            rating = 4.7
        ),
        phone = "0661 85 47 96",
        email = "americanburgerkouba@gmail.com",
        deliveryPrice = 500,
        deliveryDuration = 25,
        menu = RestaurantMenu(
            id = 1,
            categories = listOf()
        ),
        openingTime = LocalTime.of(9,0),
        closingTime = LocalTime.of(23,0),
        deliveryPersons = mutableListOf(
            DeliveryPerson(id = 1, fullName = "John Doe", phone = "0662 123 456"),
            DeliveryPerson(id = 2,fullName = "Jane Smith", phone = "0663 654 321")
        ),
        socialMediaLinks = mutableListOf(
            SocialMediaLink(id = 1 ,name = "Facebook", url = "American Burger"),
            SocialMediaLink(id = 2 ,name = "Instagram", url = "american.burger_")
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Light gray background
    ) {
        Box {
            // Top Image
            Image(
                painter = rememberAsyncImagePainter(restaurant.banner.imagePath), // Load image from the URL
                contentDescription = "Restaurant Banner",
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
                    .clickable {navController.popBackStack()}
            )
            // Bottom Circle Image
            Image(
                painter = rememberAsyncImagePainter(restaurant.logo.imagePath), // Load image from the URL
                contentDescription = "Restaurant Banner",
                modifier = Modifier
                    .size(70.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 35.dp)
                    .clip(CircleShape),
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
                text = "${restaurant.restaurantName}",
                fontWeight = FontWeight.Bold
            )
            IconText(icon = R.drawable.position_icon, text = "${restaurant.location.address}",0.dp)
            Text(
                text = "${restaurant.cuisineType.name}",
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
                IconText(icon = R.drawable.timer_icon, text = "${restaurant.deliveryDuration} min",0.dp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "${restaurant.deliveryPrice} DA",fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.width(16.dp))
                IconText(icon = R.drawable.eye_icon, text = "${restaurant.rating.reviewersCount}",0.dp)
                Spacer(modifier = Modifier.width(16.dp))
                IconText(icon = R.drawable.star_icon, text = "${restaurant.rating.rating}",0.dp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contact Information
            InfoSection(
                icon = R.drawable.phone_icon,
                title = "Phone",
               info =  { Text(text = "${restaurant.phone}",fontWeight = FontWeight.Medium,modifier = Modifier.padding(start = 20.dp)) }
            )
            InfoSection(
                icon = R.drawable.mail_icon,
                title = "E-mail",
                info = { Text(text = "${restaurant.email}",fontWeight = FontWeight.Medium,modifier = Modifier.padding(start = 20.dp)) }
            )

            // Social Media

            InfoSection(
                icon = R.drawable.social_icon,
                title = "Social Media",
                info = {
                    Column(modifier = Modifier.padding(start = 20.dp)) {
                        restaurant.socialMediaLinks.forEach { link ->
                            Text(
                                text = "${link.name}: ${link.url}",
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
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
                        icon = R.drawable.done, // Replace with your desired icon
                        title = "Restaurant feedbacks",
                        info = {
                            Column(modifier = Modifier.padding(start = 20.dp)) {
                                feedbackList.forEach { infoText ->
                                    Text(
                                        text = infoText,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
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
