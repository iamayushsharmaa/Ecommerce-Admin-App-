package com.example.adminblinkitclone.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adminblinkitclone.data.CounterState
import com.example.adminblinkitclone.data.PhoneAuthState
import com.example.adminblinkitclone.data.ResultState
import com.example.adminblinkitclone.repository.auth.FirebaseRepository
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel(){

    private val _authState = MutableStateFlow<PhoneAuthState>(PhoneAuthState.Idle)
    val authState: StateFlow<PhoneAuthState> = _authState.asStateFlow()

    private var verificationId: String? = null

    private val _counter = MutableStateFlow(CounterState())
    val counter = _counter.asStateFlow()

    fun sendOtp(phoneNumber: String, activity: Activity) {
        viewModelScope.launch {
            repository.createUserWithPhone(phoneNumber, activity).collect {
                when(it) {
                    is ResultState.Loading -> {
                        _authState.value = PhoneAuthState.Loading
                        Log.d("verify", "onloading bro: $phoneNumber")
                    }
                    is ResultState.Success<*> -> {
                        verificationId = repository.verificationId
                        _authState.value = PhoneAuthState.CodeSent(it.data.toString())
                    }
                    is ResultState.Failure -> {
                        _authState.value = PhoneAuthState.Error(it.exception.message ?: "Unknown Error")
                        Log.d("verify", "onfailed agians bor: $phoneNumber")
                    }
                    ResultState.Idle -> TODO()
                }
            }

        }
    }

    fun verifyOtp(otp: String, firestore: FirebaseFirestore,phoneNumber: String) {
        val credential = verificationId?.let { PhoneAuthProvider.getCredential(it, otp) }
        if (credential == null) {
            _authState.value = PhoneAuthState.Error("Invalid verification ID")
            return
        }
        viewModelScope.launch {
            repository.signInWithPhoneAuthCredential(credential,firestore,phoneNumber).collect {
                when (it) {
                    is ResultState.Loading -> {
                        _authState.value = PhoneAuthState.Loading
                    }
                    is ResultState.Success<*> -> {
                        _authState.value = PhoneAuthState.Verified(it.data.toString())
                    }
                    is ResultState.Failure -> {
                        _authState.value = PhoneAuthState.Error(it.exception.message ?: "Unknown Error")
                    }
                    ResultState.Idle -> TODO()
                }
            }
        }
    }

}