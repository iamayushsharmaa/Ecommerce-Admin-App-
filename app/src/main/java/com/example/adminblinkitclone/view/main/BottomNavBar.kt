package com.example.adminblinkitclone.view.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.adminblinkitclone.data.BottomNavItem

@Composable
fun BottomNavBar(
    modifier: Modifier,
    selectedRoute: String,
    onItemClick: (String) -> Unit
) {
    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Add,
        BottomNavItem.Profile
    )
    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (item.route == selectedRoute) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                selected = item.route == selectedRoute,
                onClick = { onItemClick(item.route) }
            )
        }
    }

}