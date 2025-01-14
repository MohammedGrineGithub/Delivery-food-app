package com.example.deliveryfoodapp.pages
import com.example.deliveryfoodapp.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import com.example.deliveryfoodapp.ui.theme.Primary
import androidx.compose.ui.res.painterResource
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.ui.theme.Grey
import com.example.deliveryfoodapp.utils.Wilayas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPage(navController : NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val topBottomMargin = (screenHeight * 0.1f)
    val leftRightMargin = (screenWidth * 0.04f)
    val context = LocalContext.current

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("Select your wilaya")
    }
    var exactLocation by remember {
        mutableStateOf("")
    }
    val currentLocation = remember {
        mutableStateOf(false)
    }

    Box (
        modifier = Modifier.fillMaxWidth()
    )
    {
        IconButton(
            onClick = { navController.popBackStack() } ,
            modifier = Modifier.align(Alignment.TopStart)
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Go Back"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = leftRightMargin,
                    top = topBottomMargin,
                    end = leftRightMargin,
                    bottom = topBottomMargin
                )
        ) {
            Column  {
                Column (
                    horizontalAlignment = Alignment.Start ,
                    verticalArrangement = Arrangement.spacedBy(4.dp) ,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Location" ,
                        textAlign = TextAlign.Start ,
                        fontSize =40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                    Text(
                        text = "Please fill all your location details" ,
                        textAlign = TextAlign.Center ,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = BlackStroke
                    )
                }
                Column (

                    verticalArrangement = Arrangement.spacedBy((screenHeight * 0.06f)) ,
                    modifier = Modifier.padding(
                        top = (screenHeight * 0.07f)
                    )
                ){
                    Box{
                        Column {
                            Text(
                                text = "Wilaya" ,
                                fontSize = 16.sp ,
                                color = Black
                            )
                            Spacer(Modifier.height(8.dp))
                            ExposedDropdownMenuBox(
                                expanded = isExpanded ,
                                onExpandedChange = { isExpanded = !isExpanded }
                            ) {
                                if (selected == "Select your wilaya" ) {
                                    TextField(
                                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                                        value = selected ,
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = Grey,
                                            unfocusedContainerColor = Grey,
                                            disabledTextColor = Black,
                                            focusedIndicatorColor = GreyStroke,
                                            unfocusedIndicatorColor = GreyStroke,
                                            disabledIndicatorColor = GreyStroke
                                        ),
                                        onValueChange = {} ,
                                        readOnly = true ,
                                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon( expanded = isExpanded)} ,
                                        placeholder = { } ,
                                        singleLine = true
                                    )
                                }
                                else {
                                    TextField(
                                        modifier = Modifier.menuAnchor().fillMaxWidth(),
                                        value = selected ,
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = Grey,
                                            unfocusedContainerColor = Grey,
                                            disabledTextColor = Black,
                                            focusedIndicatorColor = GreyStroke,
                                            unfocusedIndicatorColor = GreyStroke,
                                            disabledIndicatorColor = GreyStroke
                                        ),
                                        onValueChange = {} ,
                                        readOnly = true ,
                                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon( expanded = isExpanded)} ,
                                        placeholder = { } ,
                                        singleLine = true
                                    )
                                }
                                ExposedDropdownMenu(
                                    expanded = isExpanded,
                                    onDismissRequest = { isExpanded = false },
                                    modifier = Modifier.heightIn(max = 300.dp).fillMaxWidth().background(color = Grey)
                                ) {
                                    Wilayas.ALL_WILAYAS.forEach { wilaya ->
                                        DropdownMenuItem(
                                            text = { Text(text = "${wilaya.id}- ${wilaya.name}") },
                                            onClick = {
                                                selected = wilaya.name
                                                isExpanded = false
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                            modifier = Modifier.fillMaxWidth().background(color = Grey)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Box {
                        Column {
                            Text(
                                text = "Exact Location",
                                fontSize = 16.sp ,
                                color = Black
                            )
                            Spacer(Modifier.height(8.dp))
                            TextField(
                                value = exactLocation,
                                onValueChange = { exactLocation = it },
                                placeholder = { Text("Example: Sommame - Bab Ezzouar" , color = GreyStroke) },
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Grey,
                                    unfocusedContainerColor = Grey,
                                    disabledTextColor = Black,
                                    focusedIndicatorColor = GreyStroke,
                                    unfocusedIndicatorColor = GreyStroke,
                                    disabledIndicatorColor = GreyStroke
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }
                    }
                    Box {
                        Column {

                            Text(
                                text = "Current Location",
                                fontSize = 16.sp,
                                color = Black
                            )
                            Spacer(Modifier.height(8.dp))
                            Button(
                                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).border(width=1.dp , color = GreyStroke , shape =RoundedCornerShape(20.dp) ) ,
                                colors = ButtonDefaults.buttonColors(Color.White),
                                onClick = {
                                    currentLocation.value = authenticatedUser.location.checkLocationPermissionAndServices(context)
                                }
                            )  {
                                if (!currentLocation.value) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.vector),
                                        contentDescription = "Location",
                                        modifier = Modifier.size(32.dp),
                                        tint = Color.Unspecified
                                    )
                                }
                                else{
                                    Icon(
                                        painter = painterResource(id = R.drawable.done),
                                        contentDescription = "Location",
                                        modifier = Modifier.size(32.dp),
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                        }

                    }
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally ,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {

                            // TODO : save these values into authenticatedUser

                            if ( selected == "Select your wilaya" || exactLocation == ""){
                                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                authenticatedUser.location.wilayaId = Wilayas.ALL_WILAYAS.find { it.name == selected }!!.id
                                authenticatedUser.location.address = exactLocation
                                navController.popBackStack()
                            }
                        } ,
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)) ,
                        colors = ButtonDefaults.buttonColors(Primary),
                        shape = RectangleShape ,

                        ) {
                        Text(
                            text = "Confirm" ,
                            textAlign = TextAlign.Center,
                            color = Color.White ,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }

}
