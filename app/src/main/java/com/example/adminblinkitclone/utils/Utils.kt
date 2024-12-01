package com.example.adminblinkitclone.utils

object Utils {

    fun getRandomId(): String {
        val characters = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..25)
            .map { characters.random() }
            .joinToString("")
    }
}