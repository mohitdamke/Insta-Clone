package com.example.instaclonre.domain.use_case.user

import com.example.instaclonre.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(
        userId: String, name: String,
        bio: String, userName: String, websiteUrl: String
    ) = repository.setUserDetail(userId, name, bio, userName, websiteUrl)

}