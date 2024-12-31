package com.example.deliveryfoodapp.pages

import CategoryItem
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.models.Restaurant
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Orange
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.ui.theme.White
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.utils.createRestaurantForTest
import com.example.deliveryfoodapp.widgets.ItemCard


@SuppressLint("NewApi")
@Composable
fun RestaurantDetailsPage(navController : NavHostController) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var selectedIndex by remember { mutableIntStateOf(0) }

    val restaurant : Restaurant = createRestaurantForTest()

    /** Main page **/
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        /** Banner photo with restaurant logo **/
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            /** Banner **/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight / 4 + 38.dp)
            ){
                AsyncImage(
                    model = restaurant.banner.imagePath,
                    contentDescription = "banner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight / 4),
                    contentScale = ContentScale.FillBounds
                )

                /** Circle back icon button **/
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp, start = 12.dp)
                ){

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(60.dp))
                            .background(White)
                            .padding(all = 16.dp)
                    ){
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
            }
            /** Logo **/
            AsyncImage(
                model = restaurant.logo.imagePath,
                contentDescription = "logo",
                modifier = Modifier
                    .size(88.dp)
                    .align(Alignment.BottomCenter)
                    .clip(shape = CircleShape)
                    .clickable { navController.navigate(Routes.PLUS_INFO_PAGE) },
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
                text = restaurant.restaurantName,
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
                    text = restaurant.location.address,
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
                        text = "${restaurant.deliveryDuration.minute} min",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                /** price **/
                Text(
                    text = "${restaurant.deliveryPrice} DA",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )

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
                        text = restaurant.rating.reviewersCount.toString(),
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
                        text = restaurant.rating.rating.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

            }
        }

        /** Restaurant menu **/
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                /** Title menu **/
                Text(
                    text = "Menu",
                    fontSize = 24.sp,
                    fontFamily = lemonFontFamily,
                )

                /** Cuisine type **/
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(R.drawable.restaurant_icon),
                        contentDescription = "rating",
                        modifier = Modifier.size(28.dp),
                        tint = Primary
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = restaurant.cuisineType.name,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Primary,
                        fontFamily = lemonFontFamily
                    )
                }
            }

            /** Divider **/
            HorizontalDivider(thickness = 1.dp, color = GreyStroke)

            Spacer(Modifier.height(8.dp))

            /** Row list of categories **/
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                itemsIndexed(restaurant.menu.categories) { index, category ->
                    CategoryItem(
                        categoryName = category.name,
                        isSelected = index == selectedIndex,
                        onClick = { selectedIndex = index }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            /** List of the items of each category **/

            AnimatedContent(
                targetState = selectedIndex,
                transitionSpec = {
                    if (targetState > initialState) {
                        // Slide in from the right and out to the left (<--)
                        slideInHorizontally(initialOffsetX = { it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { -it }) using
                                SizeTransform(clip = false)
                    } else {
                        // Slide in from the left and out to the right (-->)
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                slideOutHorizontally(targetOffsetX = { it }) using
                                SizeTransform(clip = false)
                    }
                },
                label = ""
            ) { targetIndex ->
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(restaurant.menu.categories[targetIndex].items) { index, item ->
                        ItemCard(
                            item = item,
                            onClick = {
                            }
                        )
                    }
                }
            }

        }
    }

}