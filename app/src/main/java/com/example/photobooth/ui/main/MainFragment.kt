package com.example.photobooth.ui.main

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.library.data.Photo
import com.example.library.data.PhotoRepo
import com.example.library.utils.PhotoUtil
import com.example.photobooth.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var capturedFile: Uri? = null
    private val compositeDisposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        take_photo.setOnClickListener {
            context?.let {
                capturedFile = PhotoUtil.captureIntent(it) { intent ->
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                }
            }
            view_photo.setOnClickListener { viewModel.viewPhotos() }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != REQUEST_IMAGE_CAPTURE || resultCode != RESULT_OK) {
            return
        }
        capturedFile?.let {
            val editText = EditText(context)
            AlertDialog.Builder(activity)
                .setTitle("Enter a name for this photo")
                .setView(editText)
                .setNegativeButton(android.R.string.cancel) { _, _ ->
                    //TODO delete this file
                    capturedFile = null
                }
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    val name = editText.text.toString()
                    val photo = Photo(name = name, uri = it.toString(), createdAt = Date())
                    viewModel.savePhoto(context!!, photo).addTo(compositeDisposable)
                }
                .create().show()

        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
