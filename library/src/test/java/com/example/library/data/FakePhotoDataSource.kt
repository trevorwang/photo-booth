package com.example.library.data

import com.example.library.data.source.PhotoDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class FakePhotoDataSource : PhotoDataSource {

    private val photos = arrayListOf<Photo>()

    override fun savePhoto(photo: Photo): Completable {
        return Completable.fromAction {
            photos.add(photo)
        }
    }

    override fun loadAllPhotos(): Observable<List<Photo>> {
        return Observable.fromIterable(photos).toList().toObservable()
    }

    override fun updatePhoto(photo: Photo): Completable {
        return Completable.fromAction {
            photos.removeIf { it.id == photo.id }
            photos.add(photo)
        }
    }

    override fun deleteAllPhotos(): Completable {
        return Completable.fromAction {
            photos.clear()
        }
    }
}