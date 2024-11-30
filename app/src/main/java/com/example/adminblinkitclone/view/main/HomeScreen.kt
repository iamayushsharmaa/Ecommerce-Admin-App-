package com.example.adminblinkitclone.view.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.adminblinkitclone.R
import com.example.adminblinkitclone.data.Category
import kotlinx.coroutines.delay


private val TOP_BAR_HEIGHT = 250.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            CollapsingTopBar(lazyListState)
        },
        content = { paddingValues ->
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { SectionTitle("Best Sellers") }
                item { HorizontalCategoryRow() }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { SectionTitle("Category") }
                item { HorizontalCategoryRow() }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { SectionTitle("Best Sellers") }
                item { HorizontalCategoryRow() }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { SectionTitle("Category") }
                item { HorizontalCategoryRow() }

            }
        }
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun HorizontalCategoryRow() {
    val categories = listOf(
        Category("Milk", R.drawable.png_milk),
        Category("Curd", R.drawable.png_milk),
        Category("Vegetables", R.drawable.png_milk),
        Category("Iron", R.drawable.png_milk),
        Category("Hair Dryer", R.drawable.png_milk),
        Category("Cookies", R.drawable.png_milk)
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            ItemDesign(category)
        }
    }
}

data class Category(val title: String, val image: Int)


@Preview
@Composable
private fun MainScreenPreview() {
    HomeScreen(rememberNavController())
}