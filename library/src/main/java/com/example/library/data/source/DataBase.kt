package com.example.library.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.library.data.Photo

@Database(entities = [Photo::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao


    companion object {
        fun createDb(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "app_db")
//        .enableMultiInstanceInvalidation() // uncomment this if run in multiple processes
                .build()
        }
    }
}