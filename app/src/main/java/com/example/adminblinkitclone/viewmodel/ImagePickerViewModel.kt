package com.example.adminblinkitclone.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImagePickerViewModel : ViewModel() {

    private val _selectedImages = MutableLiveData<List<Uri>>()
    val selectedImages: LiveData<List<Uri>> = _selectedImages

    fun addSelectedImages(uris: List<Uri>) {
        val currentImages = _selectedImages.value.orEmpty()
        val newImages = currentImages + uris
        _selectedImages.value = newImages.take(6) // Ensure the list doesn't exceed 6 images
    }
    fun removeImage(uri: Uri) {
        _selectedImages.value = _selectedImages.value?.filterNot { it == uri }
    }

}