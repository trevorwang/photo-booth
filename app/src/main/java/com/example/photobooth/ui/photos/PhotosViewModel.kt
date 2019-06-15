package com.example.photobooth.ui.photos

import androidx.lifecycle.ViewModel
import com.example.library.data.Photo
import com.example.library.data.PhotoRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


data class PhotoListState(val photos: List<Photo>)

interface PhotoStateChangeListener {
    fun onStateChanged(state: PhotoListState)
}

class PhotosViewModel : ViewModel() {

    var listener: PhotoStateChangeListener? = null

    fun loadAllPhotos(): Disposable {
        return PhotoRepo.getPhotos().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                listener?.onStateChanged(PhotoListState(list.map { it }))
            }, {
                //Handler error
                it.printStackTrace()
            })
    }
}