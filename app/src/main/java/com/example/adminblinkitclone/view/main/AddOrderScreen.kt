package com.example.adminblinkitclone.view.main

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminblinkitclone.data.ProductItems
import com.example.adminblinkitclone.objects.Constants
import com.example.adminblinkitclone.viewmodel.FirestoreViewModel
import com.example.adminblinkitclone.viewmodel.ImagePickerViewModel
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrderScreen(firestoreViewModel: FirestoreViewModel, firestore: FirebaseFirestore) {

    val imagePickerViewModel = ImagePickerViewModel()

    val context = LocalContext.current
    val activity = context as Activity
    val imageUriList by imagePickerViewModel.selectedImages.observeAsState(emptyList())

    var productTitle by remember { mutableStateOf("") }
    var quantityInKg by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var noOfStocks by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }

    var isProductTitleInvalid by remember { mutableStateOf(false) }
    var isQuantityInKgInvalid by remember { mutableStateOf(false) }
    var isUnitInvalid by remember { mutableStateOf(false) }
    var isPriceInvalid by remember { mutableStateOf(false) }
    var isNoOfStocksInvalid by remember { mutableStateOf(false) }
    var isProductCategoryInvalid by remember { mutableStateOf(false) }
    var isProductTypeInvalid by remember { mutableStateOf(false) }
    var isImageUriListInvalid by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
            ),
            navigationIcon = {
                Icon(
                    modifier = Modifier.padding(start = 5.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back icon"
                )
            },
            title = {
                Text(
                    text = "Add Your Product",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Please fill all the required fields.",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(Modifier.height(13.dp))

            OutlinedTextField(
                value = productTitle,
                onValueChange = { productTitle = it },
                placeholder = {
                    Text(
                        text = "Product title"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 6.dp),
                )
            if (isProductTitleInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f) // Equal weight
                        .padding(horizontal = 0.dp) // Padding applied to the column
                ) {
                    OutlinedTextField(
                        value = quantityInKg,
                        onValueChange = { quantityInKg = it },
                        placeholder = {
                            Text(
                                text = "Quantity in kg"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 6.dp, end = 8.dp),
                    )
                    if (isQuantityInKgInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        placeholder = {
                            Text(
                                text = "Price (in Rs)"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 6.dp, end = 8.dp),
                    )
                    if (isPriceInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

                }

                Spacer(Modifier.width(15.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    DropDown(
                        items = Constants.units,
                        selectedItem = unit,
                        onItemSelected = { item ->
                            unit = item
                            println("Unit: $item")
                        },
                        label = "Unit",
                        modifier = Modifier
                            .width(100.dp)
                            .height(70.dp)
                            .padding(),
                    )
                    if (isUnitInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

                    OutlinedTextField(
                        value = noOfStocks,
                        onValueChange = { noOfStocks = it },
                        placeholder = {
                            Text(
                                text = "No. of stock"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 5.dp),
                        )
                    if (isNoOfStocksInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

                }
            }
            DropDown(
                items = Constants.allProductCategory,
                selectedItem = productCategory,
                onItemSelected = { item ->
                    productCategory = item
                    println("Selected item: $item")
                },
                label = "Product Category",
                modifier = Modifier.padding(10.dp)
            )
            if (isProductCategoryInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

            DropDown(
                items = Constants.allProductTypes,
                selectedItem = productType,
                onItemSelected = { item ->
                    productType = item
                    println("Selected item: $item")
                },
                label = "Product Type",
                modifier = Modifier.padding(10.dp)
            )

            if (isProductTypeInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

            Text(
                text = "Add Images",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            )

            if (isImageUriListInvalid) Text("This field is required", color = Color.Red, fontSize = 12.sp)

            AddImageRow(imageViewModel = imagePickerViewModel)

            Button(
                onClick = {
                    isProductTitleInvalid = productTitle.isEmpty()
                    isQuantityInKgInvalid = quantityInKg.isEmpty()
                    isUnitInvalid = unit.isEmpty()
                    isPriceInvalid = price.isEmpty()
                    isNoOfStocksInvalid = noOfStocks.isEmpty()
                    isProductCategoryInvalid = productCategory.isEmpty()
                    isProductTypeInvalid = productType.isEmpty()
                    isImageUriListInvalid = imageUriList.isEmpty()


                    if (!isProductTitleInvalid && !isQuantityInKgInvalid && !isUnitInvalid &&
                        !isPriceInvalid && !isNoOfStocksInvalid && !isProductCategoryInvalid &&
                        !isProductTypeInvalid && !isImageUriListInvalid
                    ) {
                        val productItem = ProductItems(
                            productTitle = productTitle,
                            quantityInKg = quantityInKg,
                            priceInRs = price,
                            unit = unit,
                            noOfStocks = noOfStocks,
                            productCategory = productCategory,
                            productType = productType,
                            productImages = imageUriList
                        )
                            firestoreViewModel.addProduct(productItem, firestore,activity)

                    } else{
                        Toast.makeText(activity, "Please fill all the information.", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 19.dp, vertical = 8.dp),
                colors =  ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                )
            ){
                Text(
                    text = "Add Item",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddOrderPreview() {

}