package com.example.adminblinkitclone.repository.firestore

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.adminblinkitclone.data.ProductItems
import com.example.adminblinkitclone.viewmodel.FirestoreViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.UUID


interface FirestoreRepository {

    suspend fun addProduct(productItems: ProductItems, firestore: FirebaseFirestore,context: Context): Boolean
    suspend fun updateProduct(productItems: ProductItems, firestore: FirebaseFirestore): Boolean
    suspend fun deleteProduct(productItems: ProductItems, firestore: FirebaseFirestore): Boolean

}

class FirestoreRepositoryImpl : FirestoreRepository {
    override suspend fun addProduct(productItems: ProductItems, firestore: FirebaseFirestore,context: Context) : Boolean {
        return try {
            val productId = UUID.randomUUID().toString() // Generates a unique ID
            firestore.collection("Products").document(productId)
                .set(productItems)
                .await()
            Toast.makeText(context, "Product added successfully", Toast.LENGTH_SHORT).show()
            true
        }catch (e : Exception){
            Log.d("addindb", "error : not added ")
            false
        }
    }
    override suspend fun updateProduct(productItems: ProductItems,firestore: FirebaseFirestore): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(productItems: ProductItems,firestore: FirebaseFirestore): Boolean {
        TODO("Not yet implemented")
    }

}