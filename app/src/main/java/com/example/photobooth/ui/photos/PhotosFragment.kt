package com.example.photobooth.ui.photos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.library.data.Photo
import com.example.photobooth.R
import com.example.photobooth.ui.BaseFragment
import com.example.photobooth.ui.ImagePreviewActivity
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.photos_fragment.*

class PhotosFragment : BaseFragment(), PhotoStateChangeListener {
    var adapter = PhotoAdapter()
    private lateinit var viewModel: PhotosViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.photos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
        adapter.itemClickListener = object : PhotoAdapter.OnItemClickListener {
            override fun onItemClicked(photo: Photo) {
                val intent = Intent(context, ImagePreviewActivity::class.java)
                intent.putExtra("image_uri", photo.uri)
                startActivity(intent)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PhotosViewModel::class.java)
        viewModel.listener = this
        viewModel.loadAllPhotos().addTo(compositeDisposable)
    }

    override fun onStateChanged(state: PhotoListState) {
        adapter.photos = state.photos
    }
}