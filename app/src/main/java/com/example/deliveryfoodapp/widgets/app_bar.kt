package com.example.deliveryfoodapp.widgets

import androidx.compose.runtime.Composable
import com.example.deliveryfoodapp.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import com.example.deliveryfoodapp.ui.theme.BlackStroke
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomAppBar(text : String, onClick: () -> Unit){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ){
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                modifier = Modifier.size(16.dp)
            )
        }
        Text(
            text = text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Box(
        ){}
    }

}