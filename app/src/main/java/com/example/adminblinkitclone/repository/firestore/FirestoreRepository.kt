package com.example.adminblinkitclone.repository.firestore

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.adminblinkitclone.data.ProductItems
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject


interface FirestoreRepository {
    suspend fun addProduct(productItems: ProductItems, context: Context): Boolean
    suspend fun updateProduct(productItems: ProductItems): Boolean
    suspend fun deleteProduct(productItems: ProductItems): Boolean
    suspend fun getProducts() : Flow<List<ProductItems>>
}

class FirestoreRepositoryImpl  @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreRepository {
    override suspend fun addProduct(productItems: ProductItems,context: Context) : Boolean {
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
    override suspend fun updateProduct(productItems: ProductItems): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(productItems: ProductItems): Boolean {
        TODO("Not yet implemented")
    }
    override suspend fun getProducts(): Flow<List<ProductItems>> = callbackFlow {
        val listenerRegistration = firestore.collection("Products").addSnapshotListener { snapshot, error ->
            try {
                if (error != null) {
                    Log.d("tag", "getProducts: error ${error.message}")
                    trySend(emptyList()) // Send empty list if there's an error
                    return@addSnapshotListener
                }
                Log.d("tag", "getProducts: 1")
                val products = snapshot?.toObjects(ProductItems::class.java) ?: emptyList()
                trySend(products) // Send the fetched products
                Log.d("tag", "getProducts: successfully")
            } catch (e: Exception) {
                Log.d("tag", "getProducts: exception ${e.message}")
                trySend(emptyList()) // Send empty list in case of exception
            }
        }
        awaitClose {
            listenerRegistration.remove()
        }
    }



}