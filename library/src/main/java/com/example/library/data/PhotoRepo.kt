package com.example.library.data

import com.example.library.data.source.PhotoDataSource
import io.reactivex.Completable
import io.reactivex.Observable

object PhotoRepo {
    private lateinit var dataSource: PhotoDataSource

    fun savePhoto(photo: Photo): Completable {
        return dataSource.savePhoto(photo)
    }

    fun getPhotos(): Observable<List<Photo>> {
        return dataSource.loadAllPhotos()
    }

    fun setup(dataSource: PhotoDataSource) {
        this.dataSource = dataSource
    }

    fun deleteAllPhotos(): Completable {
        return dataSource.deleteAllPhotos()
    }
}