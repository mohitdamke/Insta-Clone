package com.example.instaclonre.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.instaclonre.R
import com.example.instaclonre.navigation.Screens
import com.example.instaclonre.presentation.viewmodel.authentication.AuthViewModel
import com.example.instaclonre.util.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.instagram_icon),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(
            text = "Login",
            fontSize = 40.sp,
            fontWeight = FontWeight.W900,
            fontFamily = FontFamily.SansSerif,
            color = Color(0xFF3AD8A8)
        )

        Column(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Email",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500, color = Gray,
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = Gray,
                    focusedTextColor = Gray,
                    unfocusedTextColor = Gray
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "",
                        modifier = Modifier.padding(10.dp), tint = Gray
                    )
                },
                label = {
                    Text(
                        text = "Type your email", fontSize = 16.sp,
                        fontWeight = FontWeight.W600, color = Gray,
                        fontFamily = FontFamily.SansSerif
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "Password",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500, color = Gray,
                fontFamily = FontFamily.SansSerif, textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Gray,
                    unfocusedBorderColor = Gray,
                    focusedTextColor = Gray,
                    unfocusedTextColor = Gray
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Lock,
                        contentDescription = "",
                        modifier = Modifier.padding(10.dp), tint = Gray
                    )
                },
                label = {
                    Text(
                        text = "Type your password", fontSize = 16.sp,
                        fontWeight = FontWeight.W600, color = Gray,
                        fontFamily = FontFamily.SansSerif
                    )
                }, visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(top = 30.dp))
            Button(
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(context, "Enter all fields", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    } else {
                            authViewModel.signIn(email, password)
                    }
                },
                modifier = Modifier, colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3AD8A8),
                )
            ) {
                Text(
                    text = "LOGIN",
                    fontSize = 20.sp, color = Gray,
                    fontWeight = FontWeight.W600, textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Or Sign Up Using",
                    fontSize = 18.sp, fontWeight = FontWeight.W600,
                    color = Gray
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = "Sign UP",
                    fontSize = 18.sp, fontWeight = FontWeight.W900,
                    color = Color(0xFF3AD8A8), modifier = Modifier.clickable {
                        navController.navigate(Screens.SignUpScreen.route) {
                            launchSingleTop = true
                        }
                    })
            }
        }
    }
    when (val response = authViewModel.signInState.value) {

        is Resource.Loading -> {
            Toast.makeText(context, "Login Loading 23333", Toast.LENGTH_SHORT)
                .show()
            CircularProgressIndicator(
            )
        }

        is Resource.Success -> {
            if (response.data == true) {

            LaunchedEffect(response.data) {
                Toast.makeText(context, "Login Successful 23333", Toast.LENGTH_SHORT)
                    .show()
                    Toast.makeText(context, "Login Successful 23", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screens.ProfileScreen.route) {
                        popUpTo(Screens.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        is Resource.Error -> {
            Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
        }

    }

}