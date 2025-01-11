package com.example.deliveryfoodapp.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.authenticatedUser
import com.example.deliveryfoodapp.backend_services.user_api.UserEndpoints
import com.example.deliveryfoodapp.models.AppImage
import com.example.deliveryfoodapp.models.Item
import com.example.deliveryfoodapp.models.OrderItem
import com.example.deliveryfoodapp.models.UserCart
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.local_storage_services.repositories.OrderItemRepository
import com.example.deliveryfoodapp.local_storage_services.repositories.UserCartRepository
import com.example.deliveryfoodapp.ui.theme.Black
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.White
import com.example.deliveryfoodapp.utils.Routes
import com.example.deliveryfoodapp.local_storage_services.room.RoomUserCart


@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(true) } // Track loading state

    // Get user id from local storage
    val userID = Pref.getUserID()
    authenticatedUser.id = if (userID != -1) userID else 1 // later remove that 1

    val u = remember { mutableStateOf(authenticatedUser) }

    LaunchedEffect(1) {
        try {
            val user = UserEndpoints.fetchUserById(authenticatedUser.id)
            u.value = user
            Toast.makeText(context, u.value.fullName, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            isLoading.value = false
        }
    }


    /** Get all user carts from SqlLite into authenticatedUser **/
    val allUserCarts : List<RoomUserCart> = UserCartRepository.getAllUserCarts().orEmpty()

    authenticatedUser.carts.addAll(
        allUserCarts.map { cart ->
            val orderItems = OrderItemRepository.getAllOrderItemsForUserCart(cart.id).orEmpty()
            UserCart(
                id = cart.id,
                orderItems = orderItems.map { o ->
                    OrderItem(
                        id = o.id,
                        item = Item(
                            id = o.item.itemID,
                            name = o.item.name,
                            ingredients = o.item.ingredients,
                            price = o.item.price,
                            photo = AppImage(id = o.item.itemID, imagePath = o.item.photo)
                        ),
                        note = o.note,
                        itemQuantity = o.itemQuantity
                    )
                }.toMutableList(),
                restaurantID = cart.restaurantID
            )
        }
    )

    val homeNavController = rememberNavController()

    // Observe current back stack entry as state
    val currentRoute = homeNavController.currentBackStackEntryFlow
        .collectAsState(initial = homeNavController.currentBackStackEntry)
        .value?.destination?.route

    val items = listOf(
        Routes.HOME_PAGE,
        Routes.MY_ORDERS_PAGE,
        Routes.PROFILE_PAGE
    )
    val icons = listOf(
        painterResource(id = R.drawable.home_icon),
        painterResource(id = R.drawable.my_orders_icon),
        painterResource(id = R.drawable.profile_icon)
    )
    val titles = listOf("Home", "My Orders", "Profile")

    if (isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
    else {
        Scaffold(
            bottomBar = {
                CustomNavigationBar(
                    items = items,
                    icons = icons,
                    titles = titles,
                    currentRoute = currentRoute,
                    onItemClick = { route ->
                        homeNavController.navigate(route) {
                            popUpTo(homeNavController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = homeNavController,
                startDestination = Routes.HOME_PAGE,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Routes.HOME_PAGE) { HomePage(navController) }
                composable(Routes.MY_ORDERS_PAGE) { MyOrdersPage(navController) }
                composable(Routes.PROFILE_PAGE) { ProfilePage(navController) }
            }
        }
    }
}


@Composable
fun CustomNavigationBar(
    items: List<String>,
    icons: List<androidx.compose.ui.graphics.painter.Painter>,
    titles: List<String>,
    currentRoute: String?,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, route ->
                val isSelected = currentRoute == route
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = icons[index],
                            contentDescription = titles[index],
                            tint = if (isSelected) Primary else Black,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    label = {
                        Text(titles[index], color = if (isSelected) Primary else Black)
                    },
                    selected = isSelected,
                    onClick = { onItemClick(route) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
