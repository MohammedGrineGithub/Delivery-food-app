package com.example.deliveryfoodapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryfoodapp.R
import com.example.deliveryfoodapp.ui.theme.Black
import com.example.deliveryfoodapp.ui.theme.Primary
import com.example.deliveryfoodapp.ui.theme.White
import com.example.deliveryfoodapp.utils.Routes


@Composable
fun HomeScreen(navController: NavHostController) {
    val homeNavController = rememberNavController()
    val currentBackStackEntry = homeNavController.currentBackStackEntry
    val currentRoute = currentBackStackEntry?.destination?.route

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
