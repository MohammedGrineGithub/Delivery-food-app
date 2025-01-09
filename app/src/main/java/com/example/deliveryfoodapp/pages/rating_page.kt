package com.example.deliveryfoodapp.pages


import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.models.Rating
import com.example.deliveryfoodapp.ui.theme.lemonFontFamily
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.widgets.PrincipalButton
import androidx.compose.material3.Text as Text
@Composable
fun RatingPage(navController: NavHostController) {
    // State for the selected rating
    var selectedRating by remember { mutableStateOf(3) }

    // State for the feedback text
    var feedback by remember { mutableStateOf("") }

    // Function to handle the "Submit" button click
    fun submitRating() {
        // Simulate creating a Rating object (backend model)
        val rating = Rating(
            id = 1, // Simulate an ID (in a real app, this would come from the backend)
            reviewersCount = 1, // Simulate a single reviewer (the current user)
            rating = selectedRating.toDouble() // Convert the selected rating to Double
        )

        // Simulate sending the rating and feedback to the backend


        // Optionally, navigate to another screen or show a success message
        navController.navigate(Routes.HOME_SCREEN){
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Enjoy your meal",
            modifier = Modifier.padding(top = 20.dp),
            color = Color.Black,
            fontFamily = lemonFontFamily,
            fontSize = 24.sp
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "How do you rate the restaurant?",
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomRatingBar(
                    rating = selectedRating,
                    onRatingChanged = { newRating ->
                        selectedRating = newRating // Update the rating state
                    }
                )
                Spacer(modifier = Modifier.height(48.dp))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Share your feedback about the service",
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                // Feedback TextField
                TextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Share your feedback") }
                )
            }
            Spacer(modifier = Modifier.height(88.dp))
            // Submit Button
            PrincipalButton(
                text = "Submit",
                onClick = { submitRating() }, // Call the submit function
            )
        }

        TextWithClickableLink(onClick = {
            // Handle the "click here" action
            navController.popBackStack()
        })
    }
}
@Composable
fun CustomRatingBar(rating: Int, onRatingChanged: (Int) -> Unit) {
    var currentRating by remember { mutableStateOf(rating) }

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume() // Consomme l'événement pour éviter les conflits
                    val newRating = ((change.position.x / size.width) * 5).toInt() + 1
                    currentRating = newRating.coerceIn(1, 5) // S'assure que la note reste entre 1 et 5
                    onRatingChanged(currentRating)
                }
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 1..5) {
                val isSelected = i <= currentRating
                Icon(
                    painter = painterResource(id = if (isSelected) R.drawable.star_fill else R.drawable.star_outline),
                    contentDescription = null,
                    tint = if (isSelected) Primary else Primary,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Composable
fun TextWithClickableLink(onClick: () -> Unit) {
    Text(
        text = buildAnnotatedString {
            append("If you want to rate after ")
            // Add clickable and underlined "click here" text
            pushStringAnnotation(tag = "click_here", annotation = "click_here")
            withStyle(style = SpanStyle(color = Primary, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)) {
                append("click here")
            }
            pop()
        },
        fontWeight = FontWeight.Medium,
        modifier = Modifier.clickable {
            // Handle the click action here
            onClick()
        }
    )
}


@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("Share your feedback") }

    TextField(
        value = text,
        onValueChange = { text = it }
    )
}