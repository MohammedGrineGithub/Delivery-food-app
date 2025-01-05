package com.example.deliveryfoodapp.pages



import android.annotation.SuppressLint
import android.os.Build
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.example.deliveryfoodapp.R
import kotlin.random.Random
import java.time.LocalDateTime
import java.time.LocalTime
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
import com.example.deliveryfoodapp.ui.theme.Primary
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import com.example.deliveryfoodapp.utils.Routes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.CardBackground
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.models.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import android.content.Context
import android.health.connect.datatypes.ExerciseRoute
import com.example.deliveryfoodapp.services.Pref.context
import com.example.deliveryfoodapp.ui.theme.Red

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController : NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val topBottomMargin = (screenHeight * 0.02f) // 11.3% of screen height
    val leftRightMargin = (screenWidth * 0.04f) // 22% of screen width
    val user = User.emptyUser()
    user.has_notification = true
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

    val wilayas = listOf(
        Wilaya(0, "None"),
        Wilaya(1, "Adrar"),
        Wilaya(2, "Chlef"),
        Wilaya(3, "Laghouat"),
        Wilaya(4, "Oum El Bouaghi"),
        Wilaya(5, "Batna"),
        Wilaya(6, "Béjaïa"),
        Wilaya(7, "Biskra"),
        Wilaya(8, "Béchar"),
        Wilaya(9, "Blida"),
        Wilaya(10, "Bouira"),
        Wilaya(11, "Tamanrasset"),
        Wilaya(12, "Tébessa"),
        Wilaya(13, "Tlemcen"),
        Wilaya(14, "Tiaret"),
        Wilaya(15, "Tizi Ouzou"),
        Wilaya(16, "Algiers"),
        Wilaya(17, "Djelfa"),
        Wilaya(18, "Jijel"),
        Wilaya(19, "Sétif"),
        Wilaya(20, "Saïda"),
        Wilaya(21, "Skikda"),
        Wilaya(22, "Sidi Bel Abbès"),
        Wilaya(23, "Annaba"),
        Wilaya(24, "Guelma"),
        Wilaya(25, "Constantine"),
        Wilaya(26, "Médéa"),
        Wilaya(27, "Mostaganem"),
        Wilaya(28, "M’Sila"),
        Wilaya(29, "Mascara"),
        Wilaya(30, "Ouargla"),
        Wilaya(31, "Oran"),
        Wilaya(32, "El Bayadh"),
        Wilaya(33, "Illizi"),
        Wilaya(34, "Bordj Bou Arreridj"),
        Wilaya(35, "Boumerdès"),
        Wilaya(36, "El Tarf"),
        Wilaya(37, "Tindouf"),
        Wilaya(38, "Tissemsilt"),
        Wilaya(39, "El Oued"),
        Wilaya(40, "Khenchela"),
        Wilaya(41, "Souk Ahras"),
        Wilaya(42, "Tipaza"),
        Wilaya(43, "Mila"),
        Wilaya(44, "Aïn Defla"),
        Wilaya(45, "Naâma"),
        Wilaya(46, "Aïn Témouchent"),
        Wilaya(47, "Ghardaïa"),
        Wilaya(48, "Relizane"),
        Wilaya(49, "Timimoun"),
        Wilaya(50, "Bordj Badji Mokhtar"),
        Wilaya(51, "Ouled Djellal"),
        Wilaya(52, "Béni Abbès"),
        Wilaya(53, "In Salah"),
        Wilaya(54, "In Guezzam"),
        Wilaya(55, "Touggourt"),
        Wilaya(56, "Djanet"),
        Wilaya(57, "El M'Ghair"),
        Wilaya(58, "El Meniaa")
    )

    val cuisineTypes = listOf(
        CuisineType(0, "None") ,
        CuisineType(1, "Algerian"),
        CuisineType(2, "Italian"),
        CuisineType(3, "Chinese"),
        CuisineType(4, "French"),
        CuisineType(5, "Japanese"),
        CuisineType(6, "Indian"),
        CuisineType(7, "Mexican"),
        CuisineType(8, "Thai"),
        CuisineType(9, "Spanish"),
        CuisineType(10, "Greek")
    )

    val restaurants = List(10) { index ->
        Restaurant(
            id = index + 1,
            restaurantName = "Restaurant $index",
            logo = AppImage(index, "https://i.ibb.co/QJcxJdL/restautantlogo.png"),
            banner = AppImage(index, "https://i.ibb.co/b2cHt8n/image.png"),
            location = Location.emptyLocation(),
            cuisineType = cuisineTypes.random(),
            rating = Rating(index,Random.nextInt(1,58), Random.nextDouble(1.0, 5.0)),
            phone = "123-456-789$index",
            email = "restaurant$index@example.com",
            deliveryPrice = 150,
            deliveryDuration = 20 ,
            menu = RestaurantMenu(1, listOf(
            )), // Assuming an empty menu for simplicity
            openingTime = LocalTime.of(8,0) ,
            closingTime = LocalTime.of(23,0)
        )
    }
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
                if (user.has_notification == false){
                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "notifiactions",
                        modifier = Modifier.size(34.dp).background(color = Color.Transparent).align(Alignment.Top),
                    )
                }
                else {
                    Icon(
                        painter = painterResource(R.drawable.notification),
                        contentDescription = "notifiactions",
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
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Black,
                            modifier = Modifier.padding(8.dp)
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
                                contentDescription = "Location",
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
            cuisineTypes.forEach { CuisineType ->
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
            wilayas.forEach { Wilaya ->
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
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ){
                        items(restaurants){
                                restaurant ->
                            Restaurant_Box(ScreenHeight = screenHeight, navController = navController, restaurant = restaurant)
                        }
                    }
                }
                else {
                }
            }
        }
    }
}
@Composable
fun Restaurant_Box(ScreenHeight : Dp, navController : NavHostController , restaurant: Restaurant) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(color = CardBackground, shape = RoundedCornerShape(20.dp)).clickable {
            navController.navigate(Routes.RESTAURANT_DETAILS_PAGE)
        }
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = restaurant.banner.imagePath,
                contentDescription = "Banner",
                modifier = Modifier.height((ScreenHeight * 0.18f)).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.padding(6.dp)
            ) {
                Row(

                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
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
                            modifier = Modifier.size(14.dp).background(color = Color.Transparent)
                                .align(Alignment.Center),
                            tint = Color.Unspecified
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(6.dp).fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier.size((ScreenHeight * 0.055f)).background(
                            shape = RoundedCornerShape((ScreenHeight * 0.065f)),
                            color = Color.Transparent
                        )
                    )
                    {
                        AsyncImage(
                            model = restaurant.logo.imagePath,
                            contentDescription = "Logo",
                            modifier = Modifier.size((ScreenHeight * 0.055f)),
                            contentScale = ContentScale.Crop
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
                                modifier = Modifier.size(12.dp)
                                    .background(color = Color.Transparent)
                                    .align(Alignment.CenterVertically),
                                tint = Color.Unspecified
                            )
                            Text(
                                text = "${restaurant.deliveryDuration}-${restaurant.deliveryDuration + 5} min",
                                fontSize = 14.sp,
                            )
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.point),
                                contentDescription = "point",
                                modifier = Modifier.size(5.dp).background(color = Color.Transparent)
                                    .align(Alignment.CenterVertically),
                                tint = BlackStroke
                            )
                            Spacer(Modifier.width(10.dp))
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