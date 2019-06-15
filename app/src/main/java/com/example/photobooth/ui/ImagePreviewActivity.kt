package com.example.photobooth.ui

import android.os.Bundle
import com.example.photobooth.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_preview_activity.*

class ImagePreviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_preview_activity)
        val uri = intent.getStringExtra("image_uri")
        Picasso.get().load(uri).into(preview)
    }
}