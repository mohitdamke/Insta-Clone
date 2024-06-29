package com.example.instaclonre.domain.repository

import com.example.instaclonre.domain.model.User
import com.example.instaclonre.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserDetail(userId: String): Flow<Resource<User>>

    fun setUserDetail(
        userId: String,
        name: String,
        bio: String,
        userName: String,
        websiteUrl: String
    ): Flow<Resource<Boolean>>

}