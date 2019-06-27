package com.example.photobooth

import android.app.Application
import com.example.library.data.PhotoRepo
import com.example.library.data.source.LocalPhotoDataSource

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PhotoRepo.setup(LocalPhotoDataSource(this))
    }
}