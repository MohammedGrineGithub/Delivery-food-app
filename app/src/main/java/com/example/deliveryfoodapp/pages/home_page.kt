package com.example.deliveryfoodapp.pages



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
import com.example.deliveryfoodapp.ui.theme.Primary
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import com.example.deliveryfoodapp.utils.Routes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.unit.Dp
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.CardBackground
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController : NavHostController) {
    val configuration = LocalConfiguration.current
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
    val cuisineTypes = listOf( "French", "Italian", "Japanese", "Chinese", "Mexican", "Indian", "Thai", "Spanish", "Greek", "Moroccan" )
    val wilayas = listOf("Adrar", "Chlef", "Laghouat", "Oum El Bouaghi", "Batna", "Béjaïa", "Biskra", "Béchar", "Blida", "Bouira", "Tamanrasset", "Tébessa", "Tlemcen", "Tiaret", "Tizi Ouzou", "Algiers", "Djelfa", "Jijel", "Sétif", "Saïda", "Skikda", "Sidi Bel Abbès", "Annaba", "Guelma", "Constantine", "Médéa", "Mostaganem", "M’Sila", "Mascara", "Ouargla", "Oran", "El Bayadh", "Illizi", "Bordj Bou Arreridj", "Boumerdès", "El Tarf", "Tindouf", "Tissemsilt", "El Oued", "Khenchela", "Souk Ahras", "Tipaza", "Mila", "Aïn Defla", "Naâma", "Aïn Témouchent", "Ghardaïa", "Relizane", "Timimoun", "Bordj Badji Mokhtar", "Ouled Djellal", "Béni Abbès", "In Salah", "In Guezzam", "Touggourt", "Djanet", "El M'Ghair", "El Meniaa")
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
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "notifiactions",
                    modifier = Modifier.size(34.dp).background(color = Color.Transparent).align(Alignment.Top),
                    tint = Primary
                )
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
                        BasicTextField(
                            value = search,
                            onValueChange = { search = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true ,
                            decorationBox = { innerTextField ->
                                if (search.isEmpty()) {
                                    Text(
                                        text = "Search restaurant",
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                }
                                innerTextField()
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
            cuisineTypes.forEachIndexed { index, Cuisine ->
                DropdownMenuItem(
                    text = { Text(text = Cuisine) },
                    onClick = {
                        cuisine = Cuisine
                        CuisineisExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    modifier = Modifier.fillMaxWidth()
                        .background(color = Grey)
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
            wilayas.forEachIndexed { index, Wilaya ->
                DropdownMenuItem(
                    text = { Text(text = Wilaya) },
                    onClick = {
                        wilaya = Wilaya
                        WilayaisExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    modifier = Modifier.background(color = Grey)
                )
            }
        }
    }
}
                    }
                }
                LazyColumn {
                    item {
                        Restaurant_Box(ScreenHeight = screenHeight , navController )
                    }
                }
            }
        }
    }
}
@Composable
fun Restaurant_Box(ScreenHeight : Dp, navController : NavHostController){
    Box(
        modifier = Modifier.fillMaxWidth().background(color = CardBackground, shape = RoundedCornerShape(20.dp)).clickable {
            navController.navigate(Routes.RESTAURANT_DETAILS_PAGE)
        }
    ){

        Column (
                verticalArrangement = Arrangement.spacedBy(2.dp) ,
                horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Banner",
                modifier = Modifier.height( (ScreenHeight*0.18f) ).fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.padding(6.dp)
            ){
                Row (

                        horizontalArrangement = Arrangement.End ,
                        verticalAlignment = Alignment.Top ,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    ){
                        Text("4.5",
                            fontSize = 14.sp)
                        Box(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ){

                            Icon(
                                painter = painterResource(id = R.drawable.rating),
                                contentDescription = "Location",
                                modifier = Modifier.size(14.dp).background(color = Color.Transparent).align(Alignment.Center),
                                tint = Color.Unspecified
                            )
                        }
                    }

                Row (
                    modifier = Modifier.padding(6.dp).fillMaxWidth()
                ){
                    Box(
                        modifier = Modifier.size((ScreenHeight*0.055f)).background(shape = RoundedCornerShape((ScreenHeight*0.065f)),color = Color.Transparent)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.restautantlogo),
                            contentDescription = "Logo",
                            modifier = Modifier.size((ScreenHeight*0.055f)),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column (
                        modifier = Modifier.padding(
                            start = 8.dp
                        )
                    ){
                        Text(text="American Burger",
                            fontSize = 14.sp ,
                            fontWeight = FontWeight.Bold
                        )
                        Row (
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.time),
                                contentDescription = "time",
                                modifier = Modifier.size(12.dp).background(color = Color.Transparent).align(Alignment.CenterVertically),
                                tint = Color.Unspecified
                            )
                            Text(
                                text = "20-25 min" ,
                                fontSize = 14.sp ,
                            )
                            Spacer(Modifier.width(10.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.point),
                                contentDescription = "point",
                                modifier = Modifier.size(5.dp).background(color = Color.Transparent).align(Alignment.CenterVertically),
                                tint = BlackStroke
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = "150 DA" ,
                                fontSize = 14.sp ,
                            )
                        }

                    }
                }
            }
        }
    }
}
