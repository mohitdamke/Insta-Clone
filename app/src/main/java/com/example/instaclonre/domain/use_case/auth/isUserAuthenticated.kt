package com.example.instaclonre.domain.use_case.auth

import com.example.instaclonre.domain.repository.AuthRepository
import javax.inject.Inject

class isUserAuthenticated @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.isUserAuthInFirebase()

}