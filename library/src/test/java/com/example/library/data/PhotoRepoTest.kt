package com.example.library.data

import com.example.library.data.source.PhotoDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class PhotoRepoTest {
    private val photos = arrayListOf(
        Photo(1, "hello", "", Date()),
        Photo(2, "world", "", Date())
    )
    lateinit var testObserver: TestObserver<List<Photo>>
    @Before
    fun setup() {
        testObserver = TestObserver.create()
    }

    @After
    fun clean() {
        PhotoRepo.deleteAllPhotos()
    }


    @Test
    fun retrieveEmptyPhoto() {
        PhotoRepo.setup(emptyDataSource())
        PhotoRepo.getPhotos().subscribe(testObserver)
        testObserver.assertValue(arrayListOf())
        testObserver.assertComplete()
    }

    @Test
    fun retrievePhoto() {
        PhotoRepo.setup(customDataSource(photos))
        PhotoRepo.getPhotos().subscribe(testObserver)
        testObserver.assertValue(photos)
        testObserver.assertNotComplete()
    }

    @Test
    fun saveOnePhoto() {
        val photo = Photo(1, "hello", "file:///xddd", Date())
        val mock = mock<PhotoDataSource>()
        PhotoRepo.setup(mock)
        PhotoRepo.savePhoto(photo)
        verify(mock).savePhoto(photo)

    }

    @Test
    fun deleteAllPhotos() {
        val mock = mock<PhotoDataSource>()
        PhotoRepo.setup(mock)
        PhotoRepo.deleteAllPhotos()
        verify(mock).deleteAllPhotos()
    }

    private fun emptyDataSource(): PhotoDataSource {
        return mock {
            on { loadAllPhotos() } doReturn Observable.just(arrayListOf())
        }
    }

    private fun customDataSource(data: List<Photo>): PhotoDataSource {
        return mock {
            on { loadAllPhotos() } doReturn Observable.just(data).concatWith(Observable.never())
        }
    }

}