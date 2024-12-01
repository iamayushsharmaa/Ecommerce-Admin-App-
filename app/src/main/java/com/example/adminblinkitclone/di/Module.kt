package com.example.adminblinkitclone.di

import com.example.adminblinkitclone.repository.auth.FirebaseRepository
import com.example.adminblinkitclone.repository.auth.FirebaseRepositoryImpl
import com.example.adminblinkitclone.repository.firestore.FirestoreRepository
import com.example.adminblinkitclone.repository.firestore.FirestoreRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseRepository(): FirebaseRepository {
        return FirebaseRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideFirestoreRepository(firestore: FirebaseFirestore): FirestoreRepository {
        return FirestoreRepositoryImpl(firestore)
    }
}