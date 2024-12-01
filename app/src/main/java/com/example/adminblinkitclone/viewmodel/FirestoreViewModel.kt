package com.example.adminblinkitclone.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.adminblinkitclone.data.ProductItems
import com.example.adminblinkitclone.data.ProductsUiState
import com.example.adminblinkitclone.repository.firestore.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getProductData()
    }

    fun addProduct(productItems: ProductItems, context: Context) {
        viewModelScope.launch {
            firestoreRepository.addProduct(productItems,context)
            Log.d("ass", "product added succefully")
        }
    }

    fun getProductData(){
        viewModelScope.launch{
            firestoreRepository.getProducts()
                .onStart { _uiState.value = ProductsUiState.Loading
                    Log.d("getdata", "loading ")
                }
                .catch { exception->
                    _uiState.value = ProductsUiState.Error(exception.localizedMessage ?: "Error fetcching products")
                    Log.d("getdata", "error ")
                }
                .collect{ products ->
                    _uiState.value = ProductsUiState.Success(products)
                    Log.d("getdata", "success data fetching ${products} ")
                }
        }

    }

}