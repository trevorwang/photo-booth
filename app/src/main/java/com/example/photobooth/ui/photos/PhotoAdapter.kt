package com.example.photobooth.ui.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.library.data.Photo
import com.example.library.utils.toSimple
import com.example.photobooth.R
import com.squareup.picasso.Picasso

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {
    interface OnItemClickListener {
        fun onItemClicked(photo: Photo)
    }

    var itemClickListener: OnItemClickListener? = null

    var photos = listOf<Photo>()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return PhotoHolder(itemView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val photo = photos[position]
        holder.bindData(photo)
        holder.itemView.setOnClickListener { itemClickListener?.onItemClicked(photo) }
    }

    class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val photoName: TextView = itemView.findViewById(R.id.photo_name)
        val createAt: TextView = itemView.findViewById(R.id.created_at)

        fun bindData(photo: Photo) {
            Picasso.get().load(photo.uri).placeholder(R.mipmap.ic_launcher).into(imageView)
            photoName.text = photo.name
            createAt.text = photo.createdAt.toSimple()
        }
    }
}