package com.example.deliveryfoodapp.widgets


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun LogoutDialog(
    onDismiss: () -> Unit, // Fermer la boîte de dialogue
    onConfirm: () -> Unit // Confirmer la déconnexion
) {
    AlertDialog(
        onDismissRequest = onDismiss, // Fermer la boîte de dialogue lorsque l'utilisateur clique en dehors
        title = {
            Text(
                text = "Log out",
                fontSize = 20.sp,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = "Are you sure to disconnect?",
                fontSize = 16.sp,
                color = Color.Gray
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm // Action de confirmation
            ) {
                Text(
                    text = "Yes",
                    color = Color.Red,
                    fontSize = 16.sp
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss // Action d'annulation
            ) {
                Text(
                    text = "Cancel",
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    )
}

