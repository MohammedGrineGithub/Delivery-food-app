package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MyOrdersPage(navController : NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the entire screen space
            .padding(16.dp), // Add some padding around the column
        horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
        //verticalArrangement = Arrangement.SpaceEvenly // Space out items evenly
    ){
        Text("MyOrdersPage ...")
    }

}