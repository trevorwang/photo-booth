package com.example.library.data

import com.example.library.data.source.PhotoDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class PhotoRepo(val dataSource: PhotoDataSource) {

    fun savePhoto(photo: Photo): Completable {
        return dataSource.savePhoto(photo)
    }

    fun getPhotos(): Observable<Array<Photo>> {
        return dataSource.loadAllPhotos()
    }
}