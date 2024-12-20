package com.example.adminblinkitclone.data

import android.net.Uri

data class ProductItems(
    val adminUid : String? = null,
    val productTitle: String,
    val quantityInKg: String,
    val priceInRs: String,
    val unit: String,
    val noOfStocks: String,
    val productCategory: String,
    val productType: String,
    val productImages: List<Uri>,
    val productRating : Float? = null
)
