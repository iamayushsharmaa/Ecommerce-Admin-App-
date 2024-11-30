package com.example.adminblinkitclone.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminblinkitclone.data.ProductItems
import com.example.adminblinkitclone.repository.firestore.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel(){

    fun addProduct(productItems: ProductItems, firestore: FirebaseFirestore, context: Context) {
        viewModelScope.launch {
            firestoreRepository.addProduct(productItems,firestore,context)
            Log.d("ass", "product added succefully")
        }
    }


}