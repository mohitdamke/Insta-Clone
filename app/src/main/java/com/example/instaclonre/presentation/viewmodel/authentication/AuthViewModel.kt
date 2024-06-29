package com.example.instaclonre.presentation.viewmodel.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaclonre.domain.use_case.auth.AuthenticationUseCases
import com.example.instaclonre.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthenticationUseCases
) : ViewModel() {
    val isUserAuthenticated get() = authUseCase.isUserAuthenticated()

    private val _signInState = mutableStateOf<Resource<Boolean>>(Resource.Success(false))
    val signInState: State<Resource<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Resource<Boolean>>(Resource.Success(false))
    val signUpState: State<Resource<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Resource<Boolean>>(Resource.Success(false))
    val signOutState: State<Resource<Boolean>> = _signOutState

    private val _firebaseAuthState = mutableStateOf(false)
    val firebaseAuthState: State<Boolean> = _firebaseAuthState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.isFirebaseSignIn(email = email, password = password).collect {
                _signInState.value = it
            }
        }
    }


    fun signUp(username: String, email: String, password: String) {
        viewModelScope.launch {
            authUseCase.isFirebaseSignUp(username = username, email = email, password = password)
                .collect {
                    _signUpState.value = it
                }
        }
    }


    fun signOut() {
        viewModelScope.launch {
            authUseCase.isFirebaseSignOut().collect {
                _signOutState.value = it
                if (it == Resource.Success(true)) {
                    _signOutState.value = Resource.Success(false)

                }
            }
        }
    }

    fun getFirebaseAuthState() {
        viewModelScope.launch {
            authUseCase.firebaseAuthStatus().collect {
                _firebaseAuthState.value = it
            }
        }
    }
}