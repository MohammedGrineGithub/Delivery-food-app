package com.example.deliveryfoodapp.pages



import android.annotation.SuppressLint
import android.widget.Toast
import com.example.deliveryfoodapp.R
import androidx.compose.ui.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deliveryfoodapp.ui.theme.Black
import com.example.deliveryfoodapp.ui.theme.Grey
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import com.example.deliveryfoodapp.utils.Routes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.example.deliveryfoodapp.ui.theme.CardBackground
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.models.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.backend_services.restaurant_api.RestaurantEndpoints
import com.example.deliveryfoodapp.currentRestaurant
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.utils.CuisineTypes
import com.example.deliveryfoodapp.utils.Wilayas
import java.time.LocalTime

@SuppressLint("NewApi", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController : NavHostController) {

    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(true) }

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val topBottomMargin = (screenHeight * 0.02f) // 11.3% of screen height
    val leftRightMargin = (screenWidth * 0.04f) // 22% of screen width

    var search by remember {
        mutableStateOf("")
    }
    var CuisineisExpanded by remember {
        mutableStateOf(false)
    }
    var WilayaisExpanded by remember {
        mutableStateOf(false)
    }
    var wilaya by remember {
        mutableStateOf("Wilaya")
    }
    var cuisine by remember {
        mutableStateOf("Cuisine")
    }

    val restaurants = remember {
        mutableStateOf(mutableListOf<Restaurant>())
    }
    val hasNotification = remember { mutableStateOf(authenticatedUser.has_notification) }

    LaunchedEffect(1) {
        try {
            val res : MutableList<Restaurant> = RestaurantEndpoints.fetchAllRestaurants()
            restaurants.value = res
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            isLoading.value = false
        }
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }else {
        Box (
            modifier = Modifier.fillMaxSize()
        ) {
            Box (

                modifier = Modifier.fillMaxWidth().padding(
                    top = (screenHeight * 0.02f) - 6.dp
                ),

                ){
                Button(
                    onClick = {
                        navController.navigate(Routes.NOTIFICATIONS_PAGE)
                    },
                    modifier = Modifier.align(Alignment.TopEnd).padding(0.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                ){
                    if (!hasNotification.value){
                        Icon(
                            painter = painterResource(id = R.drawable.notification),
                            contentDescription = "has notification",
                            tint = GreyStroke,
                            modifier = Modifier.size(34.dp).background(color = Color.Transparent).align(Alignment.Top),
                        )
                    }
                    else {
                        Icon(
                            painter = painterResource(R.drawable.notification_on),
                            contentDescription = "no notification",
                            tint = Primary,
                            modifier = Modifier.size(34.dp).background(color = Color.Transparent).align(Alignment.Top),
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = leftRightMargin,
                        top = topBottomMargin,
                        end = leftRightMargin,
                    )
            ){
                Column(
                    verticalArrangement = Arrangement.spacedBy((screenHeight * 0.02f)),
                    modifier = Modifier.fillMaxSize()

                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy((screenHeight * 0.02f)),
                    ) {
                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Text(
                                text = "Home",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                color = Black,
                                fontSize = 28.sp,
                                fontFamily = lemonFontFamily
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Grey)
                                .padding(2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = Black,
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                            )
                            TextField(
                                value = search,
                                onValueChange = { search = it },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    disabledTextColor = Black,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent
                                ),
                                placeholder = { Text("Search") },
                                modifier = Modifier.weight(1f),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                keyboardActions = KeyboardActions(
                                    onSearch = {

                                    }
                                ),
                                trailingIcon = {
                                    if (search.isNotEmpty()) {
                                        Button(
                                            onClick = {

                                            } ,
                                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                                        ) {
                                            Icon(Icons.Default.Check, contentDescription = "Search" ,
                                                tint = Black)
                                        }
                                    }
                                }
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            Box(
                                modifier = Modifier.background(
                                    color = Grey,
                                    shape = RoundedCornerShape(100.dp)
                                ).padding(10.dp).width((screenWidth * 0.06f)),

                                ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.filter),
                                    contentDescription = "filter",
                                    modifier = Modifier.size(26.dp).background(color = Color.Transparent)
                                        .align(
                                            Alignment.Center
                                        ),
                                    tint = Color.Unspecified
                                )
                            }
                            Row(
                                horizontalArrangement =  Arrangement.spacedBy(6.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = CuisineisExpanded,
                                    onExpandedChange = { CuisineisExpanded = !CuisineisExpanded },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    if (cuisine == "Cuisine") {
                                        TextField(
                                            value = cuisine,
                                            onValueChange = {},
                                            readOnly = true,
                                            trailingIcon = {
                                                ExposedDropdownMenuDefaults.TrailingIcon(
                                                    expanded = CuisineisExpanded
                                                )
                                            },
                                            modifier = Modifier
                                                .background(Grey, RoundedCornerShape(12.dp))
                                                .menuAnchor(),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                focusedTextColor = Color.Black,
                                                unfocusedTextColor = Color.Black,
                                                disabledTextColor = Color.Gray,
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                disabledIndicatorColor = Color.Transparent
                                            ),
                                            textStyle = LocalTextStyle.current.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal
                                            )
                                        )
                                    } else {
                                        TextField(
                                            value = cuisine,
                                            onValueChange = {},
                                            readOnly = true,
                                            singleLine = true,
                                            trailingIcon = {
                                                ExposedDropdownMenuDefaults.TrailingIcon(
                                                    expanded = CuisineisExpanded
                                                )
                                            },
                                            modifier = Modifier
                                                .background(Grey, RoundedCornerShape(12.dp))
                                                .menuAnchor(),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                focusedTextColor = Color.Black,
                                                unfocusedTextColor = Color.Black,
                                                disabledTextColor = Color.Gray,
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                disabledIndicatorColor = Color.Transparent
                                            ),
                                            textStyle = LocalTextStyle.current.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal
                                            )
                                        )
                                    }
                                    ExposedDropdownMenu(
                                        expanded = CuisineisExpanded,
                                        onDismissRequest = { CuisineisExpanded = false },
                                        modifier = Modifier.heightIn(max = 300.dp)
                                            .background(color = Grey)
                                    ) {
                                        CuisineTypes.ALL_CUISINE_TYPES.forEach { CuisineType ->
                                            DropdownMenuItem(
                                                text = { Text(text = "${CuisineType.id}- ${CuisineType.name}") },
                                                onClick = {
                                                    cuisine = CuisineType.name
                                                    CuisineisExpanded = false
                                                },
                                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                                modifier = Modifier.fillMaxWidth().background(color = Grey)
                                            )
                                        }
                                    }
                                }
                                ExposedDropdownMenuBox(
                                    expanded = WilayaisExpanded,
                                    onExpandedChange = { WilayaisExpanded = !WilayaisExpanded },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    if (wilaya == "Wilaya") {
                                        TextField(
                                            value = wilaya,
                                            onValueChange = {},
                                            readOnly = true,
                                            trailingIcon = {
                                                ExposedDropdownMenuDefaults.TrailingIcon(
                                                    expanded = WilayaisExpanded
                                                )
                                            },
                                            modifier = Modifier
                                                .background(Grey, RoundedCornerShape(12.dp))
                                                .menuAnchor(),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                focusedTextColor = Color.Black,
                                                unfocusedTextColor = Color.Black,
                                                disabledTextColor = Color.Gray,
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                disabledIndicatorColor = Color.Transparent
                                            ),
                                            textStyle = LocalTextStyle.current.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal
                                            )
                                        )
                                    } else {
                                        TextField(
                                            value = wilaya,
                                            onValueChange = {},
                                            readOnly = true,
                                            singleLine = true,
                                            trailingIcon = {
                                                ExposedDropdownMenuDefaults.TrailingIcon(
                                                    expanded = WilayaisExpanded
                                                )
                                            },
                                            modifier = Modifier
                                                .background(Grey, RoundedCornerShape(12.dp))
                                                .menuAnchor(),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = Color.Transparent,
                                                unfocusedContainerColor = Color.Transparent,
                                                focusedTextColor = Color.Black,
                                                unfocusedTextColor = Color.Black,
                                                disabledTextColor = Color.Gray,
                                                focusedIndicatorColor = Color.Transparent,
                                                unfocusedIndicatorColor = Color.Transparent,
                                                disabledIndicatorColor = Color.Transparent
                                            ),
                                            textStyle = LocalTextStyle.current.copy(
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal
                                            )
                                        )
                                    }
                                    ExposedDropdownMenu(
                                        expanded = WilayaisExpanded,
                                        onDismissRequest = { WilayaisExpanded = false },
                                        modifier = Modifier.heightIn(max = 300.dp)
                                            .background(color = Grey)
                                    ) {
                                        Wilayas.ALL_WILAYAS.forEach { Wilaya ->
                                            DropdownMenuItem(
                                                text = { Text(text = "${Wilaya.id}- ${Wilaya.name}") },
                                                onClick = {
                                                    wilaya = Wilaya.name
                                                    WilayaisExpanded = false
                                                },
                                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                                modifier = Modifier.fillMaxWidth().background(color = Grey)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if ( ( cuisine == "Cuisine" || cuisine == "None" ) && (wilaya == "Wilaya" || wilaya == "None") )
                    {
                        LazyColumn (
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ){
                            items(restaurants.value){
                                    restaurant ->
                                Restaurant_Box(ScreenHeight = screenHeight, navController = navController, restaurant = restaurant)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Restaurant_Box(ScreenHeight: Dp, navController: NavHostController, restaurant: Restaurant) {

    fun isWithinOperatingHours(openingTime: LocalTime, closingTime: LocalTime): Boolean {
        val currentTime = LocalTime.now()
        return if (closingTime.isAfter(openingTime) || closingTime == openingTime) {
            currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)
        } else {
            currentTime.isAfter(openingTime) || currentTime.isBefore(closingTime)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = CardBackground, shape = RoundedCornerShape(20.dp))
            .clickable {
                currentRestaurant = restaurant.copy()
                navController.navigate(Routes.RESTAURANT_DETAILS_PAGE)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ScreenHeight * 0.18f)
            ) {
                AsyncImage(
                    model = restaurant.banner.imagePath,
                    contentDescription = "Banner",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 16.dp, topEnd = 16.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                            )
                        )
                        .then(
                            if (!isWithinOperatingHours(
                                    restaurant.openingTime,
                                    restaurant.closingTime
                                )
                            ) Modifier.blur(16.dp) else Modifier
                        ),
                    contentScale = ContentScale.Crop
                )
                if (!isWithinOperatingHours(restaurant.openingTime, restaurant.closingTime)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = 16.dp, topEnd = 16.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                                )
                            )
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
            // Add the rest of the UI below this, unchanged.
            Box(
                modifier = Modifier.padding(6.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        truncateToOneDecimal(restaurant.rating.rating),
                        fontSize = 14.sp
                    )
                    Box(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.rating),
                            contentDescription = "Rating",
                            modifier = Modifier
                                .size(14.dp)
                                .background(color = Color.Transparent)
                                .align(Alignment.Center),
                            tint = Color.Unspecified
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size((ScreenHeight * 0.055f))
                            .background(
                                shape = RoundedCornerShape((ScreenHeight * 0.065f)),
                                color = Color.Transparent
                            )
                    ) {
                        AsyncImage(
                            model = restaurant.logo.imagePath,
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size((ScreenHeight * 0.055f))
                                .clip(shape = CircleShape),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Column(
                        modifier = Modifier.padding(
                            start = 8.dp
                        )
                    ) {
                        Text(
                            text = restaurant.restaurantName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.time),
                                contentDescription = "time",
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(color = Color.Transparent)
                                    .align(Alignment.CenterVertically),
                                tint = Color.Unspecified
                            )
                            Text(
                                text = "${restaurant.deliveryDuration} - ${restaurant.deliveryDuration + 5} min",
                                fontSize = 14.sp,
                            )
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.point),
                                contentDescription = "point",
                                modifier = Modifier
                                    .size(5.dp)
                                    .background(color = Color.Transparent)
                                    .align(Alignment.CenterVertically)
                            )
                            Spacer(Modifier.width(10.dp))

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.moto_icon),
                                    contentDescription = "delivery",
                                    modifier = Modifier
                                        .size(14.dp)
                                        .background(color = Color.Transparent)
                                        .align(Alignment.CenterVertically)
                                )
                                Text(
                                    text = "${restaurant.deliveryPrice} DA",
                                    fontSize = 14.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


fun truncateToOneDecimal(number: Double): String {
    val numberString = number.toString()
    val decimalIndex = numberString.indexOf('.')

    return if (decimalIndex != -1) {
        // Truncate to one digit after the decimal
        numberString.substring(0, decimalIndex + 2)
    } else {
        // If there's no decimal point, return the original number
        numberString
    }
}