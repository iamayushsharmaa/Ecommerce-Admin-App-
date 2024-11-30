package com.example.adminblinkitclone.objects

import com.example.adminblinkitclone.repository.auth.FirebaseRepository
import com.example.adminblinkitclone.repository.auth.FirebaseRepositoryImpl
import com.example.adminblinkitclone.repository.firestore.FirestoreRepository
import com.example.adminblinkitclone.repository.firestore.FirestoreRepositoryImpl
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
    fun provideFirebaseRepository(): FirebaseRepository {
        return FirebaseRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideFirestoreRepository(): FirestoreRepository {
        return FirestoreRepositoryImpl()
    }
}