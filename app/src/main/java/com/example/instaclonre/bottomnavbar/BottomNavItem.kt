package com.example.instaclonre.bottomnavbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.instaclonre.navigation.Screens

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Feed : BottomNavItem(Screens.FeedsScreen.route, Icons.Default.Home, "Home")
    object Search : BottomNavItem(Screens.SearchScreen.route, Icons.Default.Search, "Search")
    object Profile : BottomNavItem(Screens.ProfileScreen.route, Icons.Default.Person, "Profile")
}