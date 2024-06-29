package com.example.instaclonre.domain.use_case.user

import com.example.instaclonre.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetails @Inject constructor(private val  repository: UserRepository) {
    operator fun invoke(userId: String) = repository.getUserDetail(userId)

}