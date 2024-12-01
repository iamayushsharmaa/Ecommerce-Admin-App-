package com.example.adminblinkitclone.view.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.adminblinkitclone.view.login.OtpVerificationScreen
import com.example.adminblinkitclone.view.login.PhoneSignIn
import com.example.adminblinkitclone.view.main.MainScreen
import com.example.adminblinkitclone.viewmodel.FirebaseViewModel
import com.example.adminblinkitclone.viewmodel.FirestoreViewModel
import com.example.adminblinkitclone.viewmodel.SaveCategoryViewModel
import com.google.firebase.firestore.FirebaseFirestore



@Composable
fun NavigationScreen(
    firebaseViewModel: FirebaseViewModel,
    isUserLogin: Boolean,
    firestore: FirebaseFirestore,
    firestoreViewModel: FirestoreViewModel,
    savecategoryViewModel: SaveCategoryViewModel
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen"
        //if (isUserLogin) "homeScreen" else "signInScreen"
    ){

        composable("signInScreen") {
            PhoneSignIn(
                navController,
                firebaseViewModel = firebaseViewModel
            )
        }
        composable("otpVerificationScreen/{phoneNumber}",
            arguments = listOf(navArgument("phoneNumber"){
                type = NavType.StringType
            })
        ) {backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber")
            if (phoneNumber != null) {
                OtpVerificationScreen(navController, phoneNumber,firebaseViewModel,firestore)
            }
        }
        composable("mainScreen") {
            MainScreen(navController,firestoreViewModel,savecategoryViewModel)
        }

    }
}