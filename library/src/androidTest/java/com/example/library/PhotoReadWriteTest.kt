package com.example.library

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.library.data.Photo
import com.example.library.data.source.AppDataBase
import com.example.library.data.source.PhotoDao
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class PhotoReadWriteTest {

    private lateinit var photoDao: PhotoDao
    private lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()

        photoDao = db.photoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writePhotoAndReadAll() {
        val photo = Photo(1, "hello", "file://filepath", Date())
        photoDao.insert(photo)
        assertThat(photoDao.loadAllUsers().filter { it.id == 1L }).hasSize(1)
    }

}