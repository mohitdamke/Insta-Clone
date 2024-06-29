package com.example.instaclonre.di

import com.example.instaclonre.data.repository.AuthRepositoryImpl
import com.example.instaclonre.data.repository.UserRepositoryImpl
import com.example.instaclonre.domain.repository.AuthRepository
import com.example.instaclonre.domain.repository.UserRepository
import com.example.instaclonre.domain.use_case.auth.AuthenticationUseCases
import com.example.instaclonre.domain.use_case.auth.FirebaseAuthStatus
import com.example.instaclonre.domain.use_case.auth.FirebaseSignIn
import com.example.instaclonre.domain.use_case.auth.FirebaseSignOut
import com.example.instaclonre.domain.use_case.auth.FirebaseSignUp
import com.example.instaclonre.domain.use_case.auth.isUserAuthenticated
import com.example.instaclonre.domain.use_case.user.GetUserDetails
import com.example.instaclonre.domain.use_case.user.SetUserDetails
import com.example.instaclonre.domain.use_case.user.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InstaModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthRepository {
        return AuthRepositoryImpl(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(repository: AuthRepository) = AuthenticationUseCases(
        isUserAuthenticated = isUserAuthenticated(repository),
        firebaseAuthStatus = FirebaseAuthStatus(repository),
        isFirebaseSignIn = FirebaseSignIn(repository),
        isFirebaseSignUp = FirebaseSignUp(repository),
        isFirebaseSignOut = FirebaseSignOut(repository)

    )

    @Provides
    @Singleton
    fun provideUserRepository(firebaseFirestore: FirebaseFirestore): UserRepository{
    return UserRepositoryImpl(firebaseFirestore)
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository) = UserUseCases(
        getUserDetails = GetUserDetails(repository),
        setUserDetails = SetUserDetails(repository),

    )

}