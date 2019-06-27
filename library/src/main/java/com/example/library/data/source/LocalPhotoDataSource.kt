package com.example.library.data.source

import android.content.Context
import com.example.library.data.Photo
import io.reactivex.Completable
import io.reactivex.Observable

class LocalPhotoDataSource(context: Context) : PhotoDataSource {
    private val db = AppDataBase.createDb(context)
    override fun savePhoto(photo: Photo): Completable {
        return Completable.fromAction {
            db.photoDao().insert(photo)
        }

    }

    override fun loadAllPhotos(): Observable<Array<Photo>> {
        return Observable.fromCallable {
            db.photoDao().loadAllPhotos()
        }
    }

    override fun updatePhoto(photo: Photo): Completable {
        return Completable.fromAction {
            db.photoDao().update(photo)
        }
    }
}