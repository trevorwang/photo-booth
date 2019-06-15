package com.example.photobooth.ui

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.photobooth.R
import kotlinx.android.synthetic.main.image_preview_activity.*

class ImagePreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_preview_activity)

        val uri = intent.getStringExtra("image_uri")
        contentResolver.openInputStream(Uri.parse(uri)).let {
            val bitmap = BitmapFactory.decodeStream(it)
            preview.setImageBitmap(bitmap)
        }
    }
}