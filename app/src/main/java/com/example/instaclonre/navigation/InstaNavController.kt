package com.example.instaclonre.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instaclonre.presentation.screens.FeedsScreen
import com.example.instaclonre.presentation.screens.LoginScreen
import com.example.instaclonre.presentation.screens.ProfileScreen
import com.example.instaclonre.presentation.screens.SearchScreen
import com.example.instaclonre.presentation.screens.SignUpScreen
import com.example.instaclonre.presentation.screens.SplashScreen

@Composable
fun InstaNavController(paddingValues: PaddingValues, navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

        composable(route = Screens.FeedsScreen.route) {
            FeedsScreen(navController = navController)
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}