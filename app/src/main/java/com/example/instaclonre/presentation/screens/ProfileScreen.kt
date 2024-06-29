package com.example.instaclonre.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonre.presentation.viewmodel.authentication.AuthViewModel
import com.example.instaclonre.presentation.viewmodel.user.UserViewModel
import com.example.instaclonre.util.Resource


@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    userViewModel.getUserInfo()
    when (val response = userViewModel.getUserData.value) {

        is Resource.Loading ->
        {
            CircularProgressIndicator()
        }
        is Resource.Success ->
        {
            if(response.data!=null){
                val obj = response.data
                val selectedTabIndex by remember {
                    mutableStateOf(0)
                }
            }
        }
        is Resource.Error ->
        {
        }
    }

    userViewModel.setUserInfo(
        "mohitdamke",
        "mohit",
        "Hi my name is mohit",
        "www.google.com"
    )


    Scaffold(
        bottomBar = {
            BottomNavigationMenu(
                selectedItem = BottomNavigationItem.PROFILE,
                navController
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Text(text = "Profile Page", fontSize = 30.sp, fontWeight = W700)

        }
    }
}
