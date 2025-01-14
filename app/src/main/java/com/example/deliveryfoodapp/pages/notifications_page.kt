package com.example.deliveryfoodapp.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.backend_services.user_api.UserEndpoints
import com.example.deliveryfoodapp.models.Notification
import com.example.deliveryfoodapp.ui.theme.GreyStroke
import com.example.deliveryfoodapp.ui.theme.Red
import com.example.deliveryfoodapp.ui.theme.Secondary
import com.example.deliveryfoodapp.utils.DateTimeManipulation
import com.example.deliveryfoodapp.utils.OrderStatuses.Companion.getStatusColorFromValue
import com.example.deliveryfoodapp.widgets.CustomAppBar

@SuppressLint("NewApi", "MutableCollectionMutableState")
@Composable
fun NotificationsPage(navController: NavHostController) {

    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(true) }

    val notifications = remember {
        mutableStateOf(mutableListOf<Notification>())
    }
    val secondUppercaseIndex = remember { mutableIntStateOf(-1) }
    val normalPart = remember { mutableStateOf("") }
    val statusValue = remember { mutableStateOf("") }
    val statusColor = remember { mutableStateOf(Red) }

    LaunchedEffect(Unit) {
        try {

            notifications.value = UserEndpoints.fetchAllUserNotificationsByUserID(authenticatedUser.id)
            if (authenticatedUser.has_notification){
                authenticatedUser.has_notification = false
                UserEndpoints.updateUserHasNotifications(authenticatedUser.id)
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            isLoading.value = false
        }
    }


    val scrollState = rememberLazyListState()


    if (isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }else {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(20.dp)
        ){
            /** App bar **/
            CustomAppBar(
                text = "Notifications",
                onClick = {navController.popBackStack()}
            )

            Spacer(modifier = Modifier.height(12.dp))
            /** Text All notifications displayed here **/
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "All notification are display here",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light
                    )
                )
            }

            /** List of notifications **/
            if (notifications.value.isNotEmpty()){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = scrollState

                ) {
                    item {
                        Spacer(modifier = Modifier.height(51.dp))
                    }
                    items(notifications.value)
                    { notification ->
                        secondUppercaseIndex.intValue = notification.message.indexOfFirst { it.isUpperCase() && it != notification.message[0] }
                        normalPart.value = notification.message.substring(0, secondUppercaseIndex.intValue)
                        statusValue.value = notification.message.substring(secondUppercaseIndex.intValue)
                        statusColor.value = getStatusColorFromValue(statusValue.value)
                        Notification_Item(
                            notification,
                            normalPart.value,
                            statusValue.value,
                            statusColor.value
                        )
                    }
                }
            }else {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        text = "No notification yet ):",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }


}


@SuppressLint("NewApi")
@Composable
fun Notification_Item(
    notification: Notification,
    normalPart : String,
    statusValue : String,
    statusColor : Color
) {


    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ) {
            Column {
                Row {

                    Text(
                        text = "Order from",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = notification.restaurantName,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Secondary

                        )
                    )

                }

                Spacer(modifier = Modifier.height(8.dp))

                NotificationMessage(
                    normalPart = normalPart,
                    statusValue = statusValue,
                    statusColor = statusColor
                )
            }

            Column {
                Text(
                    text = DateTimeManipulation.formatDateStringIntoDate(notification.createdAt),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = DateTimeManipulation.formatDateStringIntoTime(notification.createdAt),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }


        }
        HorizontalDivider(color = GreyStroke.copy(alpha = 5f))
        Spacer(modifier = Modifier.height(24.dp))

    }


}

@Composable
fun NotificationMessage(
    normalPart : String,
    statusValue : String,
    statusColor : Color
) {

    Row {
        Text(
            text = normalPart,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = statusValue,
            color = statusColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
