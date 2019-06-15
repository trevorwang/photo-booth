package com.example.photobooth

import android.app.Application
import com.example.library.data.PhotoRepo

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        PhotoRepo.setup(this)
    }
}