package com.example.adminblinkitclone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.adminblinkitclone.data.ProductCategory
import com.example.adminblinkitclone.data.ProductType
import com.example.adminblinkitclone.data.Unit
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SaveCategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    fun saveCategories(categories: Array<String>) {
        val categoryCollection = firestore.collection("categories")
        categories.forEach { category ->
            val categoryDocument = ProductCategory(name = category)
            categoryCollection.add(categoryDocument)
        }
    }

    fun saveTypes(types: Array<String>) {
        val typeCollection = firestore.collection("types")
        types.forEach { type ->
            val typeDocument = ProductType(name = type)
            typeCollection.add(typeDocument)
        }
    }

    fun saveUnits(units: Array<String>) {
        val unitCollection = firestore.collection("units")
        units.forEach { unit ->
            val unitDocument = Unit(name = unit)
            unitCollection.add(unitDocument)
        }
    }
}

