package com.example.photobooth.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photobooth.R
import com.example.photobooth.ui.BaseFragment
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