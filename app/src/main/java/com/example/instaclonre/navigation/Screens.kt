package com.example.instaclonre.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object LoginScreen : Screens("login_screen")
    object SignUpScreen : Screens("signup_screen")
    object FeedsScreen : Screens("feed_screen")
    object ProfileScreen : Screens("profile_screen")
    object SearchScreen : Screens("search_screen")


}