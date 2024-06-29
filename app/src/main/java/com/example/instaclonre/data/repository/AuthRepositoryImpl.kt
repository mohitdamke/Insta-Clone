package com.example.instaclonre.data.repository

import android.util.Log
import com.example.instaclonre.domain.model.User
import com.example.instaclonre.domain.repository.AuthRepository
import com.example.instaclonre.util.Constants
import com.example.instaclonre.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    private var operationSuccessful: Boolean = false

    override fun isUserAuthInFirebase(): Boolean {
        return auth.currentUser != null
    }

    override fun getFirebaseAuthStatus(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }

    }

    override fun firebaseSignIn(email: String, password: String): Flow<Resource<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Resource.Loading())
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            emit(Resource.Success(operationSuccessful))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error in the sign in"))
        }
    }


    override fun firebaseSignUp(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<Boolean>> = flow {
        operationSuccessful = false
        try {
            emit(Resource.Loading())
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                operationSuccessful = true
            }.await()
            if (operationSuccessful) {
                val userid = auth.currentUser?.uid!!
                val obj = User(
                    username = username,
                    userId = userid,
                    password = password,
                    email = email
                )
                firestore.collection(Constants.COLLECTION_NAME_USERS).document(userid).set(obj)
                    .addOnSuccessListener {
                        Log.d("TAG", "firebaseSignUp: success $userid")
                    }.await()
                emit(Resource.Success(operationSuccessful))
            } else {
                emit(Resource.Success(operationSuccessful))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error in the sign up"))
        }
    }



override fun firebaseSignOut(): Flow<Resource<Boolean>> = flow {

    try {
        emit(Resource.Loading())
        auth.signOut()
        emit(Resource.Success(true))
    } catch (e: Exception) {
        emit(Resource.Error(e.localizedMessage ?: "Error in the SignOut"))
    }
}

}