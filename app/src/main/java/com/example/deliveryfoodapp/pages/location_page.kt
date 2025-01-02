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
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import androidx.compose.ui.platform.LocalContext
import com.example.deliveryfoodapp.ui.theme.Primary
import androidx.compose.ui.res.painterResource
import com.example.deliveryfoodapp.ui.theme.Grey
import com.example.deliveryfoodapp.utils.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationPage(navController : NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val topBottomMargin = (screenHeight * 0.1f)
    val leftRightMargin = (screenWidth * 0.04f)
    val context = LocalContext.current
    val wilayas = listOf("Adrar", "Chlef", "Laghouat", "Oum El Bouaghi", "Batna", "Béjaïa", "Biskra", "Béchar", "Blida", "Bouira", "Tamanrasset", "Tébessa", "Tlemcen", "Tiaret", "Tizi Ouzou", "Algiers", "Djelfa", "Jijel", "Sétif", "Saïda", "Skikda", "Sidi Bel Abbès", "Annaba", "Guelma", "Constantine", "Médéa", "Mostaganem", "M’Sila", "Mascara", "Ouargla", "Oran", "El Bayadh", "Illizi", "Bordj Bou Arreridj", "Boumerdès", "El Tarf", "Tindouf", "Tissemsilt", "El Oued", "Khenchela", "Souk Ahras", "Tipaza", "Mila", "Aïn Defla", "Naâma", "Aïn Témouchent", "Ghardaïa", "Relizane", "Timimoun", "Bordj Badji Mokhtar", "Ouled Djellal", "Béni Abbès", "In Salah", "In Guezzam", "Touggourt", "Djanet", "El M'Ghair", "El Meniaa")
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf("Select your wilaya")
    }
    var exact_location by remember {
        mutableStateOf("")
    }
    var fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationText by remember { mutableStateOf("") }
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
                                    expanded = isExpanded ,
                                    onDismissRequest = { isExpanded = false } ,
                                    modifier = Modifier.heightIn(max = 300.dp).fillMaxWidth().background(color = Grey)
                                ) {
                                    wilayas.forEachIndexed { index , wilaya ->
                                        DropdownMenuItem(
                                            text = { Text(text = "${index+1}- " + wilaya) },
                                            onClick = {
                                                selected = wilaya
                                                isExpanded = false
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding ,
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
                                value = exact_location,
                                onValueChange = { exact_location = it },
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
                                    if (ContextCompat.checkSelfPermission(
                                            context,
                                            android.Manifest.permission.ACCESS_FINE_LOCATION
                                        ) == PackageManager.PERMISSION_GRANTED
                                    ) {
                                        fusedLocationClient.lastLocation
                                            .addOnSuccessListener { locationObj ->
                                                locationObj?.let {
                                                    locationText = "${it}"
                                                }
                                            }
                                            .addOnFailureListener { exception ->
                                                Toast.makeText(context, "Location not fetched: ${exception.message}", Toast.LENGTH_SHORT).show()
                                            }
                                    } else {
                                        Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )  {
                                if (locationText == "") {
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
                            navController.navigate(Routes.PROFILE_PAGE)
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