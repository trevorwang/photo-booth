package com.example.photobooth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photobooth.R
import com.example.photobooth.ui.photos.PhotosFragment

class PhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PhotosFragment())
                .commitNow()
        }
    }
}