package com.example.adminblinkitclone.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home, "home")
    object Add : BottomNavItem("Add", Icons.Filled.Add, Icons.Outlined.Add, "add")
    object Profile : BottomNavItem("Profile", Icons.Filled.Settings, Icons.Outlined.Settings, "profile")
}
