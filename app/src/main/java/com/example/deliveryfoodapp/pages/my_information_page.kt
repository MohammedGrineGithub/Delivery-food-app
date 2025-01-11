package com.example.deliveryfoodapp.pages

import android.net.Uri
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.SecondaryFill
import com.example.deliveryfoodapp.ui.theme.ibmplexsansFontFamily
import com.example.deliveryfoodapp.widgets.PrincipalButton
import java.io.ByteArrayOutputStream
import java.io.InputStream

@Composable
fun MyInformationPage(navController: NavHostController) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var base64Image by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    // Launcher for picking an image from the gallery
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            val base64 = convertImageToBase64(context, it)
            base64?.let { base64Image = it }
        }
    }

    // Simulate user data locally
    var fullName by remember { mutableStateOf(authenticatedUser.fullName) }
    var phoneNumber by remember { mutableStateOf(authenticatedUser.phone) }

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
                    .clickable { navController.popBackStack() }
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
            ProfileImage(imageUri)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Change your profile image",
                color = Primary,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { launcher.launch("image/*") },
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.pic_logo),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(20.dp)
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
                InfoRow(label = "Email", value = authenticatedUser.email)
                Spacer(modifier = Modifier.height(8.dp))
                InfoRow(label = "Address", value = authenticatedUser.location.address)
            }
        }
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Full name", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            GreenOutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Phone number", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            GreenOutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        PrincipalButton(
            text = "Save changes",
            onClick = {
                // Save changes in authenticatedUser and in backend
                authenticatedUser.fullName = fullName
                authenticatedUser.phone = phoneNumber
                base64Image?.let { base64 ->
                    authenticatedUser.photo.imagePath = base64 // Assuming `photo` has a field `imagePath` for the Base64 string
                }
                navController.popBackStack()
            },
        )
    }
}

@Composable
fun ProfileImage(imageUri: Uri?) {
    val imagePainter = if (imageUri != null) {
        rememberAsyncImagePainter(imageUri)
    } else {
        rememberAsyncImagePainter(authenticatedUser.photo.imagePath)
    }
    Image(
        painter = imagePainter,
        contentDescription = "Profile Picture",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

private fun convertImageToBase64(context: android.content.Context, uri: Uri): String? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        inputStream?.use { stream ->
            val byteArrayOutputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length: Int
            while (stream.read(buffer).also { length = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, length)
            }
            val imageBytes = byteArrayOutputStream.toByteArray()
            Base64.encodeToString(imageBytes, Base64.DEFAULT)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
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
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary
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