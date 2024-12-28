package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import com.example.deliveryfoodapp.ui.theme.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.utils.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController : NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(60.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .weight(1f)

        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Text(
                    text = "Log In",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Please choose with what you want to login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,

                )
            }
            Column{
                OutlinedButton(
                    onClick = {  },
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    border = BorderStroke(1.dp, BlackStroke),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Icon",
                            modifier = Modifier.size(30.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Log in with google",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    HorizontalDivider(modifier = Modifier.weight(1f), color = GreyStroke)
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "Or",
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.width(12.dp))
                    HorizontalDivider(modifier = Modifier.weight(1f), color = GreyStroke)
                }

                // Email TextField
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = GreyStroke) },
                    placeholder = { Text("Email", color = GreyStroke) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "email",
                            tint = Secondary,
                            modifier = Modifier
                                .width(22.dp)
                                .height(18.dp)
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Secondary.copy(alpha = 0.7f),
                        unfocusedBorderColor = GreyStroke
                    )
                )
                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password", color = GreyStroke) },
                    placeholder = { Text("Password", color = GreyStroke) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
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
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary // Set background color
                    )
                ) {
                    Text(
                        text = "Log In",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Don’t have an account ?",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Black
            )
            Spacer(Modifier.width(10.dp))
            Text(
                modifier = Modifier.clickable {
                    navController.navigate(Routes.SIGNUP_PAGE)
                },
                text = "Sign Up",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Primary,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}