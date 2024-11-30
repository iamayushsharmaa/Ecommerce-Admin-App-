package com.example.adminblinkitclone.view.main


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.adminblinkitclone.data.BottomNavItem
import com.example.adminblinkitclone.viewmodel.FirebaseViewModel
import com.example.adminblinkitclone.viewmodel.FirestoreViewModel
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun MainScreen(
    navController: NavHostController,
    firestoreViewModel: FirestoreViewModel,
    firestore: FirebaseFirestore,
    ) {
    var selectedRoute  by remember{ mutableStateOf(BottomNavItem.Home.route) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                modifier = Modifier.padding(bottom = 2.dp, top = 5.dp),
                selectedRoute = selectedRoute,
                onItemClick = {selectedRoute = it },
            )
        },
        content = { innerPadding->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (selectedRoute) {
                    BottomNavItem.Home.route -> HomeScreen(navController)
                    BottomNavItem.Add.route -> AddOrderScreen(firestoreViewModel,firestore)
                    BottomNavItem.Profile.route -> SettingScreen()
                }
            }
        }
    )

}

@Preview
@Composable
private fun HomeOne() {
   // MainScreen(navController = rememberNavController())
}