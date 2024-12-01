package com.example.adminblinkitclone.view.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminblinkitclone.R
import com.example.adminblinkitclone.data.Category
import com.example.adminblinkitclone.data.ProductsUiState
import com.example.adminblinkitclone.utils.Constants
import com.example.adminblinkitclone.utils.Constants.allProductCategory
import com.example.adminblinkitclone.utils.Constants.allProductCategoryIcon
import com.example.adminblinkitclone.view.main.cards.CategoryCard
import com.example.adminblinkitclone.view.main.cards.ItemDesign
import com.example.adminblinkitclone.viewmodel.FirestoreViewModel
import com.example.adminblinkitclone.viewmodel.SaveCategoryViewModel
import kotlinx.coroutines.delay


private val TOP_BAR_HEIGHT = 250.dp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    savecategoryViewModel: SaveCategoryViewModel,
    firestoreViewModel: FirestoreViewModel
) {

    val uiState by firestoreViewModel.uiState.collectAsState()

    val scrollState = rememberScrollState()
    val categories = allProductCategory.zip(allProductCategoryIcon) { title, imageResId ->
        Category(title, imageResId)
    }


    val searchText = remember { mutableStateOf("") }
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
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            currentPlaceholderIndex = (currentPlaceholderIndex + 1) % placeholderTexts.size
        }
    }
    LaunchedEffect(Unit) {
        savecategoryViewModel.saveCategories(allProductCategory)
        savecategoryViewModel.saveTypes(Constants.allProductTypes)
        savecategoryViewModel.saveUnits(Constants.units)
    }



    Column (modifier = Modifier.fillMaxSize()){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Home",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 19.dp, vertical = 10.dp)
            )
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
                .padding(horizontal = 10.dp,)
        ){}

        Text(
            text = "Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 19.dp, vertical = 14.dp)
            )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 8.dp)
        ) {
            items(categories){ category->
                CategoryCard(
                    modifier= Modifier.height(120.dp),
                    category = category,
                    onCategoryClick = {}
                )
            }
        }

        when(uiState){
            is ProductsUiState.Loading -> {
                Box (
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                }

            }
            is ProductsUiState.Success-> {
                val products = (uiState as ProductsUiState.Success).products
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(products){ product ->
                        ItemDesign(
                            product,
                            onProductEditClick = {
                                //navigate to new page of that particular item
                            }
                        )
                    }
                }
            }
            is ProductsUiState.Error -> {
                val errorMessage = (uiState as ProductsUiState.Error).message

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.icon_error),
                        contentDescription = "Error icon"
                    )
                    Text(
                        text = errorMessage,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}
//
//@Preview
//@Composable
//private fun MainScreenPreview() {
//    HomeScreen(rememberNavController(), viewModel())
//}