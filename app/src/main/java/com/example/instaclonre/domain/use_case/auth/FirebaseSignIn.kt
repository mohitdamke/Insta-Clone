package com.example.instaclonre.domain.use_case.auth

import com.example.instaclonre.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseSignIn @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(email: String, password: String) = repository.firebaseSignIn(email, password)

}