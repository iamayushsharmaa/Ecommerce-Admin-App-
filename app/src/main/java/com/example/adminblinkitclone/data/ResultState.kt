package com.example.adminblinkitclone.data

sealed class ProductsUiState {
    object Loading : ProductsUiState()
    data class Success(val products: List<ProductItems>) : ProductsUiState()
    data class Error(val message: String) : ProductsUiState()
}

sealed class ResultState<out T> {
    object Idle : ResultState<Nothing>() // Represents an idle or initial state
    object Loading : ResultState<Nothing>() // Represents a loading state
    data class Success<out T>(val data: T) : ResultState<T>() // Represents a success state with data
    data class Failure(val exception: Throwable) : ResultState<Nothing>() // Represents a failure state with an exception
}