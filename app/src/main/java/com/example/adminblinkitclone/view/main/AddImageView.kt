package com.example.adminblinkitclone.view.main

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.adminblinkitclone.R
import com.example.adminblinkitclone.viewmodel.ImagePickerViewModel


@Composable
fun AddImageRow(imageViewModel: ImagePickerViewModel) {
    val selectedImages by imageViewModel.selectedImages.observeAsState(emptyList())
    val maxImages = 6
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            // Only add images if there's room for more
            val availableSlots = maxImages - selectedImages.size
            if (availableSlots > 0) {
                val limitedUris = uris.take(availableSlots)
                imageViewModel.addSelectedImages(limitedUris)
            }
        }
    )

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(selectedImages) { uri ->
            ImageCard(
                uri = uri,
                onRemoveClick = { imageViewModel.removeImage(uri) }
            )
        }
        if (selectedImages.size < maxImages) {
            item {
                AddImageCard(
                    onClick = { launcher.launch("image/*") }
                )
            }
        }
    }
}


@Composable
fun ImageCard(uri : Uri, onRemoveClick: () -> Unit) {
    Box(modifier = Modifier.size(120.dp).padding(8.dp)) {
        AsyncImage(
            model = uri,
            contentScale = ContentScale.Crop,
            contentDescription = "Selected Image",
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = onRemoveClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
               // .background(shape = CircleShape)
                .size(25.dp)
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_remove),
                contentDescription = "Remove Image",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun AddImageCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .padding(8.dp)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF343434)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_add_image),
                contentDescription = "Add Image",
                tint = Color.White
            )
        }
    }
}