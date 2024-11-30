package com.example.adminblinkitclone.view.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminblinkitclone.R
import kotlinx.coroutines.delay

private val TOP_BAR_HEIGHT = 220.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapsingTopBar(lazyListState: LazyListState) {

    val searchText = remember { mutableStateOf("") }
    val isCollapsed by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 0 || lazyListState.firstVisibleItemScrollOffset > 50 }
    }
    var currentPlaceholderIndex by remember { mutableStateOf(0) }
    val placeholderTexts = listOf(
        "Search \"grocery\"",
        "Search \"electronics\"",
        "Search \"fashion\"",
        "Search \"home decor\"",
        "Search \"milk\"",
        "Search \"rice\"",
        "Search \"cookies\"",
        "Search \"pooja need\"",
        "Search \"blanket\"",
        "Search \"iron\""
    )
    val toolbarHeight by animateDpAsState(
        targetValue = if (isCollapsed) 130.dp else TOP_BAR_HEIGHT,
        animationSpec = tween(durationMillis = 300)
    )

    val contentAlpha by animateFloatAsState(
        targetValue = if (isCollapsed) 0f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            currentPlaceholderIndex = (currentPlaceholderIndex + 1) % placeholderTexts.size
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(toolbarHeight)
            .background(color = Color.Black)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        if (!isCollapsed) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
                    .alpha(contentAlpha),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome back!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { /* Handle icon click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.user_icon),
                        contentDescription = "User Icon",
                        tint = Color.White
                    )
                }
            }
        }
        SearchBar(
            query = searchText.value,
            onQueryChange = { searchText.value = it },
            onSearch = { /* Handle search action */ },
            active = false, // Ensure it remains non-expandable
            onActiveChange = {
                // Navigate instead of changing the active state
                // Example navigation logic
            },
            placeholder = { Text(text = placeholderTexts[currentPlaceholderIndex]) },
            leadingIcon = {
                Icon(
                    painter = painterResource(
                        id = if (isSystemInDarkTheme()) R.drawable.icon_search_white
                        else R.drawable.icon_search_black
                    ),
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                // Divider and search icon
                Divider(
                    modifier = Modifier
                        .height(36.dp)
                        .width(1.dp),
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp)) // To provide space between divider and icon
                Icon(
                    painter = painterResource(
                        id = if (isSystemInDarkTheme()) R.drawable.icon_microphone_white
                        else R.drawable.icon_microphone
                    ),
                    contentDescription = "voice Search Icon",
                    modifier = Modifier
                        .padding(start = 5.dp, top = 5.dp)
                )
            },
            shape = RoundedCornerShape(18.dp),
            colors = SearchBarDefaults.colors(
                containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White,
                dividerColor = Color.Gray,
                inputFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    disabledTextColor = Color.LightGray,
                    errorTextColor = Color.Red,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Gray
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = if (isCollapsed) 8.dp else 5.dp)
        ){}
    }
}
