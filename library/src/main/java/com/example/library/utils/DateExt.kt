package com.example.library.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toSimple(): String {
    val format = SimpleDateFormat("yy/MM/dd hh:mm:ss", Locale.getDefault())
    return format.format(this)
}