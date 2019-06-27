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
class PhotoDaoTest {

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
        db.clearAllTables()
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun savePhoto_retrievesPhoto() {
        val photo = Photo(1, "hello", "file://filepath", Date())
        photoDao.insert(photo)
        assertThat(photoDao.loadAllPhotos().filter { it.id == 1L }).hasSize(1)
    }

    @Test
    fun deletePhoto_emptyArrayOfRetrievedPhoto() {
        val photo = Photo(1, "hello", "file://filepath", Date())
        photoDao.insert(photo)
        photoDao.delete(photo)
        assertThat(photoDao.loadAllPhotos()).isEmpty()
    }

    @Test
    fun updatePhoto_retrievePhoto() {
        val photo = Photo(1, "hello", "file://filepath", Date())
        photoDao.insert(photo)
        val photo2 = Photo(1, "world", "file://xxxx", Date())
        photoDao.update(photo2)
        assertThat(photoDao.loadAllPhotos().filter { it.id == 1L }).containsExactly(photo2)
    }


    @Test
    fun deleteAllPhotos_emptyReceivedPhoto() {
        val photos = arrayListOf(
            Photo(1, "hello", "file://filepath", Date()),
            Photo(2, "worldi", "file://filepath", Date())
        )
        photos.forEach { photoDao.insert(it) }
        photoDao.deleteAll()
        assertThat(photoDao.loadAllPhotos()).isEmpty()
    }
}