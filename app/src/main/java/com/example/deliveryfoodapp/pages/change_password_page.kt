package com.example.deliveryfoodapp.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.backend_services.user_api.UserEndpoints
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.widgets.CustomAppBar
import com.example.deliveryfoodapp.widgets.PrincipalButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordPage(navController : NavHostController) {
    val updateTrigger = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }
    val context = LocalContext.current

    var oldPassword by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var oldPasswordVisible by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(updateTrigger.value) {
        if (updateTrigger.value) {
            try {
                UserEndpoints.changePassword(
                    id = authenticatedUser.id,
                    currentPassword = oldPassword,
                    newPassword = password
                )
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
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(all = 20.dp)
    ) {
        /** Main content **/
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            /** AppBar (Title + back arrow icon) **/
            CustomAppBar(
                text = "Change password",
                onClick = {navController.popBackStack()}
            )
            Spacer(Modifier.height(36.dp))
            Text(
                "You can simply change your password by typing the old password, then type the new password and confirm it",
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(50.dp))

            /** Password text fields **/
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                /** tap old password**/
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "Old password",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    OutlinedTextField(
                        value = oldPassword,
                        onValueChange = { oldPassword = it },
                        label = { Text("Old password", color = GreyStroke) },
                        placeholder = { Text("Old password", color = GreyStroke) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Password keyboard
                        visualTransformation = if (oldPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { oldPasswordVisible = !oldPasswordVisible }) { // Toggle visibility on click
                                Icon(
                                    painter = painterResource(
                                        id = if (oldPasswordVisible) R.drawable.password_visible else R.drawable.password_invisible
                                    ),
                                    contentDescription = "Toggle password visibility",
                                    tint = Secondary,
                                    modifier = if (oldPasswordVisible)
                                        Modifier
                                            .width(30.dp)
                                            .height(26.dp)
                                    else
                                        Modifier
                                            .width(26.dp)
                                            .height(20.dp)
                                )
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Secondary.copy(alpha = 0.7f),
                            unfocusedBorderColor = GreyStroke
                        )
                    )
                }
                /** tap new password**/
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "New password",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("New password", color = GreyStroke) },
                        placeholder = { Text("New password", color = GreyStroke) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Password keyboard
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) { // Toggle visibility on click
                                Icon(
                                    painter = painterResource(
                                        id = if (passwordVisible) R.drawable.password_visible else R.drawable.password_invisible
                                    ),
                                    contentDescription = "Toggle password visibility",
                                    tint = Secondary,
                                    modifier = if (passwordVisible)
                                        Modifier
                                            .width(30.dp)
                                            .height(26.dp)
                                    else
                                        Modifier
                                            .width(26.dp)
                                            .height(20.dp)
                                )
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Secondary.copy(alpha = 0.7f),
                            unfocusedBorderColor = GreyStroke
                        )
                    )
                }
                /** confirm new password **/
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = "Confirm new password",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirm new password", color = GreyStroke) },
                        placeholder = { Text("Confirm new password", color = GreyStroke) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Password keyboard
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) { // Toggle visibility on click
                                Icon(
                                    painter = painterResource(
                                        id = if (confirmPasswordVisible) R.drawable.password_visible else R.drawable.password_invisible
                                    ),
                                    contentDescription = "Toggle password visibility",
                                    tint = Secondary,
                                    modifier = if (confirmPasswordVisible)
                                        Modifier
                                            .width(30.dp)
                                            .height(26.dp)
                                    else
                                        Modifier
                                            .width(26.dp)
                                            .height(20.dp)
                                )
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Secondary.copy(alpha = 0.7f),
                            unfocusedBorderColor = GreyStroke
                        )
                    )
                }
            }

        }

        if (isLoading.value) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }else {
            /** Save button **/
            PrincipalButton(
                text = "Save changes",
                onClick = {
                    if (password.isEmpty()){
                        Toast.makeText(
                            context,
                            "New password must not be empty",
                            Toast.LENGTH_LONG).show()
                    } else if (password.length < 8){
                        Toast.makeText(
                            context,
                            "New Password must have more than 8 characters",
                            Toast.LENGTH_LONG).show()
                    }
                    if (confirmPassword != password) {
                        Toast.makeText(
                            context,
                            "Confirm new password must be equal to Password",
                            Toast.LENGTH_LONG).show()
                    }
                    if (password.length >= 8 && confirmPassword == password){
                        isLoading.value = true
                        updateTrigger.value = true
                    }
                }
            )
        }
    }
}