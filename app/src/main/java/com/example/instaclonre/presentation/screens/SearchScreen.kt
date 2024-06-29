package com.example.instaclonre.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonre.presentation.viewmodel.authentication.AuthViewModel


@Composable
fun SearchScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController,

    ) {
    Scaffold(
        bottomBar = { BottomNavigationMenu(selectedItem = BottomNavigationItem.SEARCH, navController) }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Text(text = "Search Page", fontSize = 30.sp, fontWeight = W700)
        }
    }
}