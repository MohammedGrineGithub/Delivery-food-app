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
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.deliveryfoodapp.models.Wilaya
import com.example.deliveryfoodapp.ui.theme.Grey
import com.example.deliveryfoodapp.widgets.PrincipalButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeLocationPage(navController : NavHostController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val topBottomMargin = (screenHeight * 0.04f)
    val leftRightMargin = (screenWidth * 0.04f)
    val context = LocalContext.current
    var location = Location.emptyLocation()
    location.address = "Hydra - Algiers"
    location.wilayaId = 16
    location.latitude = 36.7372
    location.longitude = 3.0822
    val wilayas = listOf(
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
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selected by remember {
        mutableStateOf(wilayas.find { it.id == location.wilayaId }!!.name)
    }
    var exact_location by remember {
        mutableStateOf(location.address)
    }
    var location_extracted by remember {
        mutableStateOf(false )
    }
    Box (
        modifier = Modifier.fillMaxWidth()
    )
    {
        IconButton(
            onClick = { navController.popBackStack() } ,
            modifier = Modifier.align(Alignment.TopStart).padding(top =(screenHeight * 0.032f))
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
                    horizontalAlignment = Alignment.CenterHorizontally ,
                    verticalArrangement = Arrangement.spacedBy((screenHeight * 0.06f)) ,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Change Location" ,
                        textAlign = TextAlign.Center ,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                    Text(
                        text = "you can change your location here" ,
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
                                    wilayas.forEach { wilaya ->
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
                                   location_extracted = location.checkLocationPermissionAndServices(context)
                                }
                            )  {
                                if (location_extracted == false) {
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
                    PrincipalButton(
                        text = "Save changes",
                        onClick = {
                            if ( exact_location == ""){
                                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                location.wilayaId = wilayas.find { it.name == selected }!!.id
                                location.address = exact_location
                                navController.popBackStack()
                            }
                        }
                    )
                }
            }
        }
    }

}