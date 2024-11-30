package com.example.adminblinkitclone.data

sealed class PhoneAuthState {
    object Idle : PhoneAuthState()
    object Loading : PhoneAuthState()
    data class CodeSent(val message: String) : PhoneAuthState()
    data class Verified(val message: String) : PhoneAuthState()
    data class Error(val error: String) : PhoneAuthState()
}