package com.example.adminblinkitclone.view.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(
    items: Array<String>,
    onItemSelected: (String) -> Unit,
    selectedItem: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var listItems by remember(items, selectedItem) {
        mutableStateOf(
            if (selectedItem.isNotEmpty()) {
                items.filter { x -> x.startsWith(selectedItem.lowercase(), ignoreCase = true) }
            } else {
                items.toList()
            }
        )
    }
    var selectedText by remember(selectedItem) { mutableStateOf(selectedItem) }
    
    LaunchedEffect(selectedItem){
        selectedText = selectedItem
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedText,
                label = { Text(label) },
                onValueChange = {
                    if (!expanded) {
                        expanded = true
                    }
                    selectedText = it
                    listItems = if (it.isNotEmpty()) {
                        items.filter { x -> x.startsWith(it.lowercase(), ignoreCase = true) }
                    } else {
                        items.toList()
                    }
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()

            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if (listItems.isEmpty()) {
                    DropdownMenuItem(
                        text = { Text(text = "No items found") },
                        onClick = {
                            expanded = false
                        }
                    )
                } else {
                    listItems.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                                onItemSelected(item)
                            }
                        )
                    }
                }
            }
        }
    }
}