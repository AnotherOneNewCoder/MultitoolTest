package ru.zhogin.app.features.bottomscreens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.zhogin.app.uikit.BottomNavTitle

@Composable
fun BottomNavigationView(
    navController: NavController
) {
    val listBottomItem = listOf(
        BottomItem.RandomInsult,
        BottomItem.RandomGif,
        BottomItem.Database,
        BottomItem.Settings,
    )
    BottomNavigation(
        modifier = Modifier.height(72.dp),
        backgroundColor = Color.Black
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listBottomItem.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(bottom = 4.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.BottomNavTitle,
                        softWrap = false,
                        overflow = TextOverflow.Visible,
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
                )
        }
    }
}