package com.example.instaclonre.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonre.navigation.Screens


enum class BottomNavigationItem(val icon: ImageVector, val route: String) {
    FEED(Icons.Default.Home, Screens.FeedsScreen.route),
    SEARCH(Icons.Default.Search, Screens.SearchScreen.route),
    PROFILE(Icons.Default.Person, Screens.ProfileScreen.route)
}


@Composable
fun BottomNavigationMenu(
    selectedItem: BottomNavigationItem, navController: NavController = rememberNavController()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(White)
    )
    {
        for (item in BottomNavigationItem.entries) {
            Image(imageVector = selectedItem.icon,
                contentDescription = selectedItem.name,
                modifier = Modifier
                    .weight(1f)
                    .size(40.dp)
                    .padding(4.dp)
                    .clickable {
                        navController.navigate(item.route)
                    },
                colorFilter = ColorFilter.tint(if (item == selectedItem) Black else Gray)
            )
        }
    }
}
















