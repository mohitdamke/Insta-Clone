package com.example.instaclonre.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonre.R
import com.example.instaclonre.navigation.Screens
import com.example.instaclonre.presentation.viewmodel.authentication.AuthViewModel
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authValue = authViewModel.isUserAuthenticated
    val scale = remember {
        Animatable(
            initialValue = 0f
        )
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1500, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
        )
        delay(3000)
        if (authValue) {
            navController.navigate(Screens.FeedsScreen.route) {
                popUpTo(Screens.SplashScreen.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Screens.LoginScreen.route) {
                popUpTo(Screens.SplashScreen.route) {
                    inclusive = true
                }
            }
        }

    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.instagram_icon),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale = scale.value)
        )
    }
}