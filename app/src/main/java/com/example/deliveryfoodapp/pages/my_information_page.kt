package com.example.deliveryfoodapp.pages

import android.net.Uri
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.backend_services.user_api.UserEndpoints
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.ui.theme.SecondaryFill
import com.example.deliveryfoodapp.widgets.CustomAppBar
import com.example.deliveryfoodapp.widgets.PrincipalButton
import java.io.ByteArrayOutputStream
import java.io.InputStream

@Composable
fun MyInformationPage(navController: NavHostController) {

    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val updateTrigger = remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var base64Image by remember { mutableStateOf<String?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it
            val base64 = convertImageToBase64(context, it)
            base64?.let { base64Image = it }
        }
    }

    var fullName by remember { mutableStateOf(authenticatedUser.fullName) }
    var phoneNumber by remember { mutableStateOf(authenticatedUser.phone) }

    LaunchedEffect(updateTrigger.value) {
        if (updateTrigger.value) {
            try {
                authenticatedUser.fullName = fullName
                authenticatedUser.phone = phoneNumber

                // TODO : try to save the image as binary then send it via api, else send it base64 via api and get the image url then affect that url

                /*base64Image?.let { base64 ->
                    authenticatedUser.photo.imagePath = base64
                }*/
                // Save changes in backend
                UserEndpoints.updateUserInformation(authenticatedUser)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            } finally {
                isLoading.value = false
                updateTrigger.value = false
                navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            CustomAppBar(
                text = "Personal information",
                onClick = { navController.popBackStack() }
            )
            Spacer(Modifier.height(24.dp))
            /** Main Content **/
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // Profile Image Section
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(100.dp)
                ) {
                    if (authenticatedUser.photo.imagePath.isEmpty()) {
                        Box(
                            modifier = Modifier.size(100.dp)
                                .background(Secondary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = authenticatedUser.fullName.first().toString().uppercase(),
                                style = TextStyle(
                                    fontSize = 50.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White
                                )
                            )
                        }
                    }else {
                        ProfileImage(imageUri)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { launcher.launch("image/*") }
                ) {
                    Text(
                        text = "Change your profile image",
                        color = Primary,
                        modifier = Modifier
                            .padding(top = 8.dp),
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
                    Text(text = "Full name", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(10.dp))
                    GreenOutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Phone number", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(10.dp))
                    GreenOutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                    )
                }
                Spacer(modifier = Modifier.height(150.dp))
            }
        }
        if (isLoading.value) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }else {
            PrincipalButton(
                text = "Save changes",
                onClick = {
                    isLoading.value = true
                    updateTrigger.value = true
                },
            )
        }
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
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true
    )
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Text(text = value, fontWeight = FontWeight.Normal, fontSize = 14.sp)
    }
}