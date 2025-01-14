package com.example.deliveryfoodapp.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deliveryfoodapp.ui.theme.Black
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPhoneNumberPopUp(
    phoneNumber: String,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val editedPhoneNumber = remember { mutableStateOf(phoneNumber) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Confirm your phone number",
                fontSize = 20.sp,
                color = Color.Black
            )
        },
        text = {
            OutlinedTextField(
                value = editedPhoneNumber.value,
                onValueChange = { editedPhoneNumber.value = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 16.dp,
                    bottomStart = 16.dp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Secondary.copy(alpha = 0.4f),
                    unfocusedBorderColor = GreyStroke
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true
            )
        },
        confirmButton = {
            TextButton(
                onClick = {onConfirm(editedPhoneNumber.value)}
            ) {
                Text(
                    text = "Confirm",
                    color = Primary,
                    fontSize = 16.sp
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Cancel",
                    color = Black,
                    fontSize = 16.sp
                )
            }
        }
    )
}
