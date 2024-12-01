package com.example.adminblinkitclone.data

data class DbResult(
    val success: Boolean,
    val loading : Boolean,
    val error : String? = null
)
