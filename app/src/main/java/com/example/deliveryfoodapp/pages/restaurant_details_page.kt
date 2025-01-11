package com.example.deliveryfoodapp.pages

import CategoryItem
import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.backend_services.restaurant_api.RestaurantEndpoints
import com.example.deliveryfoodapp.currentRestaurant
import com.example.deliveryfoodapp.models.Item
import com.example.deliveryfoodapp.models.RestaurantMenu
import com.example.deliveryfoodapp.models.UserCart
import com.example.deliveryfoodapp.ui.theme.Black
import com.example.deliveryfoodapp.ui.theme.CardBackground
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Orange
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.ui.theme.White
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.InfoAddItemDialog
import com.example.deliveryfoodapp.widgets.ItemCard
import java.time.LocalTime


@SuppressLint("NewApi", "DefaultLocale")
@Composable
fun RestaurantDetailsPage(navController : NavHostController) {

    fun isWithinOperatingHours(openingTime: LocalTime, closingTime: LocalTime): Boolean {
        return true
        val currentTime = LocalTime.now()
        return if (closingTime.isAfter(openingTime) || closingTime == openingTime) {
            currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)
        } else {
            currentTime.isAfter(openingTime) || currentTime.isBefore(closingTime)
        }
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(true) }

    var selectedIndex by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    /** ****************************************************************** **/
    val menu = remember {
        mutableStateOf(RestaurantMenu.emptyRestaurantMenu())
    }
    /** ****************************************************************** **/

    /** Get the user cart of that restaurant **/
    val userCart = remember {
        mutableStateOf(UserCart.emptyUserCart())
    }

    LaunchedEffect(1) {
        try {
            menu.value = RestaurantEndpoints.fetchRestaurantMenuByMenuID(currentRestaurant.menu)
            userCart.value = authenticatedUser
                .getUserCartByRestaurantID(restaurantID = currentRestaurant.id)

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            isLoading.value = false
        }
    }

    /** Main page **/
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
                            text = String.format("%.1f", currentRestaurant.rating.rating),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }

                }
            }

            if (isLoading.value) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }else {
                /** Restaurant menu **/
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 20.dp)
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
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
                                    painter = painterResource(R.drawable.cuisine_type_icon),
                                    contentDescription = "cuisine_type",
                                    modifier = Modifier.size(28.dp),
                                    tint = Primary
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    text = currentRestaurant.cuisineType.name,
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

                        if (menu.value.categories.isNotEmpty()) {
                            /** Row list of categories **/
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(36.dp)
                            ) {
                                itemsIndexed(menu.value.categories) { index, category ->
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
                                    itemsIndexed(menu.value.categories[targetIndex].items) { _, item ->
                                        ItemCard(
                                            item = item,
                                            modifier = Modifier
                                                .clickable {
                                                    selectedItem = item
                                                    showDialog = true
                                                }
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(16.dp))
                                                .background(CardBackground)
                                                .padding(all = 10.dp)
                                        )
                                    }
                                }
                            }
                        }else {
                            Box(
                                modifier = Modifier.fillMaxSize()
                            ){
                                Text(
                                    text = "This restaurant has no menu yet!",
                                    textAlign = TextAlign.Center,
                                    fontSize = 30.sp,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                    }

                    /** Button of user cart **/
                    if (userCart.value.totalItems() != 0 && isWithinOperatingHours(currentRestaurant.openingTime, currentRestaurant.closingTime))
                        Button(
                            onClick = {
                                navController.navigate(Routes.USER_CART_PAGE)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ){
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 6.dp, bottom = 6.dp)
                                            .background(Black.copy(alpha = 0.7f), shape = CircleShape)
                                            .size(24.dp),
                                    ){
                                        Text(
                                            text = "${userCart.value.totalItems()}",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = White,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                    Text(
                                        text = "Cart",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = White
                                    )
                                }
                                Text(
                                    text = "${String.format("%.1f",userCart.value.totalPrice())} DA",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = White
                                )
                            }
                        }
                }
            }
        }

    if (showDialog && selectedItem != null && isWithinOperatingHours(currentRestaurant.openingTime, currentRestaurant.closingTime)) {
        InfoAddItemDialog(
            userCart = userCart.value,
            item = selectedItem!!,
            onDismiss = {
                showDialog = false
            }
        )
    }

}