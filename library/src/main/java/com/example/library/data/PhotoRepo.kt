package com.example.library.data

import android.content.Context
import com.example.library.data.source.LocalPhotoDataSource
import com.example.library.data.source.PhotoDataSource
import io.reactivex.Completable
import io.reactivex.Observable

object PhotoRepo {
    private lateinit var dataSource: PhotoDataSource

    fun savePhoto(photo: Photo): Completable {
        return dataSource.savePhoto(photo)
    }

    fun getPhotos(): Observable<Array<Photo>> {
        return dataSource.loadAllPhotos()
    }

    fun setup(context: Context) {
        dataSource = LocalPhotoDataSource(context)
    }
}