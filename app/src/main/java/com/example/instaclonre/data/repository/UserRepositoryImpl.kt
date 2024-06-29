package com.example.instaclonre.data.repository

import com.example.instaclonre.domain.model.User
import com.example.instaclonre.domain.repository.UserRepository
import com.example.instaclonre.util.Constants
import com.example.instaclonre.util.Resource
import com.google.api.ResourceProto.resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    private var operationSuccessful = false

    override fun getUserDetail(userId: String): Flow<Resource<User>> = callbackFlow {
         Resource.Loading(true)
        val snapshotListener = firebaseFirestore.collection(Constants.COLLECTION_NAME_USERS).document(userId)
            .addSnapshotListener { snapshot, error ->
                val response = if (snapshot != null) {
                    val userInfo = snapshot.toObject(User::class.java)
                    Resource.Success(userInfo!!)
                } else {
                    Resource.Error(error?.message.toString())
                }
                trySend(response).isSuccess
            }
        Resource.Loading(false)

        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun setUserDetail(
        userId: String,
        name: String,
        bio: String,
        userName: String,
        websiteUrl: String
    ): Flow<Resource<Boolean>> = flow {
        operationSuccessful = false
        try {
            val userObj = mutableMapOf<String, String>()
            userObj["name"] = name
            userObj["bio"] = bio
            userObj["userName"] = userName
            userObj["websiteUrl"] = websiteUrl
            firebaseFirestore.collection(Constants.COLLECTION_NAME_USERS).document(userId).update(userObj as Map<String, Any>)
                .addOnSuccessListener {

                }.await()
                if(operationSuccessful){
                    emit(Resource.Success(operationSuccessful))
                }else{
                    emit(Resource.Error("Something went wrong in edit"))
                }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}




















