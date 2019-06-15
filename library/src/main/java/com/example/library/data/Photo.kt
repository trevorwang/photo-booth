package com.example.library.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Photo(
    @PrimaryKey val id: Long,
    val name: String,
    val uri: String,
    val createdAt: Date
)