package com.example.instaclonre.presentation.viewmodel.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaclonre.domain.model.User
import com.example.instaclonre.domain.use_case.user.UserUseCases
import com.example.instaclonre.util.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val userId = auth.currentUser?.uid

    private val _getUserData = mutableStateOf<Resource<User?>>(Resource.Success(null))
    val getUserData: State<Resource<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Resource<Boolean>>(Resource.Success(false))
    val setUserData: State<Resource<Boolean>> = _setUserData

    fun getUserInfo() {
        userId?.let { id ->
            viewModelScope.launch {
                userUseCases.getUserDetails(userId = id).collectLatest { resource ->
                    _getUserData.value =
                        resource as Resource<User?> // Handle null resource with Loading state
                }
            }
        } ?: run {
            _getUserData.value = Resource.Error("User not logged in")
        }
    }


    fun setUserInfo(name: String, username: String, bio: String, websiteUrl: String) {
        userId?.let { id ->
            viewModelScope.launch {
                userUseCases.setUserDetails(id, name, username, bio, websiteUrl).collectLatest { resource ->
                    _setUserData.value = resource
                }
            }
        } ?: run {
            _setUserData.value = Resource.Error("User not logged in")
        }
    }
}
