package com.example.instaclonre.domain.repository

import com.example.instaclonre.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isUserAuthInFirebase(): Boolean
    fun getFirebaseAuthStatus(): Flow<Boolean>
    fun firebaseSignIn(email: String, password: String): Flow<Resource<Boolean>>
    fun firebaseSignUp(username: String, email: String, password: String): Flow<Resource<Boolean>>
    fun firebaseSignOut(): Flow<Resource<Boolean>>


}