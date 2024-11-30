package com.example.adminblinkitclone.view.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.adminblinkitclone.ui.theme.AdminBlinkitCloneTheme
import com.example.adminblinkitclone.viewmodel.FirebaseViewModel
import com.example.adminblinkitclone.viewmodel.FirestoreViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var auth : FirebaseAuth
    val firestore= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdminBlinkitCloneTheme {
                auth = FirebaseAuth.getInstance()
                val firebaseViewModel: FirebaseViewModel by viewModels()
                val firestoreViewModel: FirestoreViewModel by viewModels()
                val isUserLogin = auth.currentUser != null
                NavigationScreen(firebaseViewModel, isUserLogin, firestore,firestoreViewModel)
            }
        }
    }
}