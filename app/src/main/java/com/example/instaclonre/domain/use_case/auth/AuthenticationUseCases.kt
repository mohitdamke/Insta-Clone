package com.example.instaclonre.domain.use_case.auth

data class AuthenticationUseCases(
    val isUserAuthenticated: isUserAuthenticated,
    val firebaseAuthStatus: FirebaseAuthStatus,
    val isFirebaseSignIn: FirebaseSignIn,
    val isFirebaseSignUp: FirebaseSignUp,
    val isFirebaseSignOut: FirebaseSignOut



)