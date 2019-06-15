package com.example.library.data.source

import com.example.library.data.Photo
import io.reactivex.Completable
import io.reactivex.Observable

interface PhotoDataSource {
    fun savePhoto(photo: Photo): Completable

    fun loadAllPhotos(): Observable<Array<Photo>>
}