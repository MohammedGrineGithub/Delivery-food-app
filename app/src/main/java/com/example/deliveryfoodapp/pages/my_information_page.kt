package com.example.deliveryfoodapp.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.SecondaryFill
import com.example.deliveryfoodapp.ui.theme.ibmplexsansFontFamily
import com.example.deliveryfoodapp.widgets.PrincipalButton


@Composable
fun MyInformationPage(navController : NavHostController) {

    var text1 by remember { mutableStateOf("Enter your full name") }
    var text2 by remember { mutableStateOf("Enter your phone number") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Back Icon and Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            // Back Icon
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {}
            )

            // Title
            Text(
                text = "Personal information",
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.Center),
                fontFamily = ibmplexsansFontFamily,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Profile Image Section
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ex_profile), // Replace with your drawable resource
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Change your profile image",
                color = Primary, // Light blue
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {},
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.pic_logo), // Replace with your drawable resource
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Info Card

        Card(
            colors = CardDefaults.cardColors(
                containerColor = SecondaryFill,
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                InfoRow(label = "Email", value = "ls_sadaoui@esi.dz")
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(label = "Address", value = "Hydra, Alger, Algeria")
            }
        }
        Column (
            horizontalAlignment = Alignment.Start
        )
        {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Full name", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            GreenOutlinedTextField(
                value = text1,
                onValueChange = { text1 = it },
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Phone number", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            GreenOutlinedTextField(
                value = text2,
                onValueChange = { text2 = it },
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        PrincipalButton(
            text = "Save changes",
            onClick = {},
        )

    }
}


@Composable
fun GreenOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary, // Green color for focused state
            unfocusedBorderColor = Primary, // Green color for unfocused state
            focusedLabelColor = Primary, // Green color for the label when focused
            cursorColor = Primary // Green color for the cursor
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold)
        Text(text = value)
    }
}
