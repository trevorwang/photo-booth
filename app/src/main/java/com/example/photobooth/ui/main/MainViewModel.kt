package com.example.photobooth.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.example.library.data.Photo
import com.example.library.data.PhotoRepo
import com.example.photobooth.R
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

const val REQUEST_IMAGE_CAPTURE = 1010

class MainViewModel : ViewModel() {
    fun savePhoto(context: Context, photo: Photo): Disposable {
        return PhotoRepo.savePhoto(photo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
            }
    }
}
