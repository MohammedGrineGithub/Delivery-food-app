package com.example.deliveryfoodapp.pages

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.animation.*
import androidx.compose.ui.platform.LocalContext
import com.example.deliveryfoodapp.widgets.PrincipalButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SignupPage(navController : NavHostController) {

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return email.matches(emailRegex.toRegex())
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // Regex to allow only digits and one leading '+'
        return phoneNumber.matches("^(\\+)?[0-9]+\$".toRegex())
    }

    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("filled just to test") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var next by remember { mutableStateOf(false) }
    val context = LocalContext.current

    /** Principal column, Items and text at the bottom **/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp)
    ) {

        /** Items, which contain The Two Texts and the SignUp buttons and text fields **/
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
                .weight(1f)

        ) {
            /** Two texts **/
            Column (
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                Text(
                    text = "Sign Up",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Please choose with what you want to signup",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    )
            }

            /** Buttons and text fields to signup **/
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)){

                /** Sign up with google Button **/
                OutlinedButton(
                    onClick = {  },
                    modifier = Modifier
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
                            text = "Sign up with google",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                /** Line of OR **/
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

                AnimatedContent(
                    targetState = next,
                    transitionSpec = {
                        slideInHorizontally { fullWidth -> if (targetState) fullWidth else -fullWidth } +
                                fadeIn() with
                                slideOutHorizontally { fullWidth -> if (targetState) -fullWidth else fullWidth } +
                                fadeOut()
                    }, label = ""
                ) { targetState ->
                    if (targetState) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .align(Alignment.Start)
                            ){
                                OutlinedButton(
                                    onClick = {
                                        next = false
                                    },
                                    border = BorderStroke(1.dp, Secondary),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = "back",
                                            tint = Secondary.copy(alpha = 0.7f),
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height(24.dp)
                                        )

                                        Spacer(Modifier.width(10.dp))

                                        Text(
                                            text = "Back",
                                            fontWeight = FontWeight.Light,
                                            fontSize = 16.sp,
                                            color = Secondary
                                        )

                                    }
                                }
                            }

                            // Password TextField
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password", color = GreyStroke) },
                                placeholder = { Text("Password", color = GreyStroke) },
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
                            OutlinedTextField(
                                value = confirmPassword,
                                onValueChange = { confirmPassword = it },
                                label = { Text("Confirm password", color = GreyStroke) },
                                placeholder = { Text("Confirm password", color = GreyStroke) },
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
                            PrincipalButton(
                                text = "Sign Up",
                                onClick = {
                                    if (password.isEmpty()){
                                        Toast.makeText(
                                            context,
                                            "Password must not be empty",
                                            Toast.LENGTH_SHORT).show()
                                    } else if (password.length < 8){
                                        Toast.makeText(
                                            context,
                                            "Password must have more than 8 characters",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                    if (confirmPassword != password) {
                                        Toast.makeText(
                                            context,
                                            "Confirm password must be equal to Password",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                    if (password.length >= 8 && confirmPassword == password){
                                        println("Signup with email/password function")
                                    }
                                }
                            )
                        }
                    } else {
                        Column (
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ){
                            /** Full name TextField **/
                            OutlinedTextField(
                                value = fullName,
                                onValueChange = { fullName = it },
                                label = { Text("Full name", color = GreyStroke) },
                                placeholder = { Text("Full name", color = GreyStroke) },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.account_circle),
                                        contentDescription = "full_name",
                                        tint = Secondary,
                                        modifier = Modifier
                                            .width(24.dp)
                                            .height(24.dp)
                                    )
                                },
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Secondary.copy(alpha = 0.7f),
                                    unfocusedBorderColor = GreyStroke
                                )
                            )
                            /** Email Text Field **/
                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                label = { Text("Email", color = GreyStroke) },
                                placeholder = { Text("Email", color = GreyStroke) },
                                modifier = Modifier
                                    .fillMaxWidth(),
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
                            /** Phone Text Field **/
                            OutlinedTextField(
                                value = phone,
                                onValueChange = { phone = it },
                                label = { Text("Phone number", color = GreyStroke) },
                                placeholder = { Text("Phone number", color = GreyStroke) },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.phone_icon),
                                        contentDescription = "phone",
                                        tint = Secondary,
                                        modifier = Modifier
                                            .width(22.dp)
                                            .height(22.dp)
                                    )
                                },
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Secondary.copy(alpha = 0.7f),
                                    unfocusedBorderColor = GreyStroke
                                )
                            )
                            /** Location Text Field **/
                            OutlinedTextField(
                                value = location,
                                onValueChange = {},
                                readOnly = true,
                                enabled = false,
                                label = { Text("Location", color = GreyStroke) },
                                placeholder = { Text("", color = GreyStroke) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { navController.navigate(Routes.LOCATION_PAGE) },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.location),
                                        contentDescription = "location",
                                        tint = Secondary,
                                        modifier = Modifier
                                            .width(26.dp)
                                            .height(26.dp)
                                    )
                                },
                                singleLine = true,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Secondary.copy(alpha = 0.7f),
                                    unfocusedBorderColor = GreyStroke,
                                    disabledBorderColor = GreyStroke
                                )
                            )

                            Spacer(Modifier.height(8.dp))

                            Box(
                                modifier = Modifier
                                    .align(Alignment.End)
                            ){
                                Button(
                                    onClick = {

                                        if (fullName.isEmpty()){
                                            Toast.makeText(
                                                context,
                                                "Full name must not be empty",
                                                Toast.LENGTH_SHORT).show()
                                        }

                                        if (email.isEmpty()){
                                            Toast.makeText(
                                                context,
                                                "Email must not be empty",
                                                Toast.LENGTH_SHORT).show()
                                        }else if (!isValidEmail(email)) {
                                            Toast.makeText(
                                                context,
                                                "Your email is not valid",
                                                Toast.LENGTH_SHORT).show()
                                        }

                                        if (phone.isEmpty()){
                                            Toast.makeText(
                                                context,
                                                "Phone number must not be empty",
                                                Toast.LENGTH_SHORT).show()

                                        }else if (!isValidPhoneNumber(phone)){
                                            Toast.makeText(
                                                context,
                                                "Your phone number is not valid, it must contain only numbers",
                                                Toast.LENGTH_SHORT).show()
                                        }

                                        if (location.isEmpty()){
                                            Toast.makeText(
                                                context,
                                                "Location must not be empty",
                                                Toast.LENGTH_SHORT).show()
                                        }

                                        if (fullName.isNotEmpty() && email.isNotEmpty() && isValidEmail(email) && phone.isNotEmpty() && isValidPhoneNumber(phone) && location.isNotEmpty()){
                                        next = true
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Primary
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Text(
                                            text = "Next",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = Color.White
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Icon(
                                            imageVector = Icons.Default.ArrowForward,
                                            contentDescription = "next",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .width(26.dp)
                                                .height(26.dp)
                                        )

                                    }
                                }
                            }
                        }
                    }
                }

            }
        }

        /** Text at the bottom **/
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Already have an account ?",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
            Spacer(Modifier.width(10.dp))
            Text(
                modifier = Modifier.clickable {
                    navController.popBackStack()
                },
                text = "Log In",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Primary,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}