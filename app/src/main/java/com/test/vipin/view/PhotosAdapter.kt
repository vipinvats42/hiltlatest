package com.test.vipin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.vipin.R
import com.test.vipin.model.Photos


class PhotosAdapter(private val photos: ArrayList<Photos>) :
    RecyclerView.Adapter<PhotosAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photos: Photos) {
            itemView.findViewById<AppCompatTextView>(R.id.tv_albumId).text =
                photos.albumId.toString()
            itemView.findViewById<AppCompatTextView>(R.id.tv_photoId).text = photos.id.toString()
            Glide.with(itemView.findViewById<ImageView>(R.id.img_url))
                .load(photos.url)
                .into(itemView.findViewById<ImageView>(R.id.img_url))
            Glide.with(itemView.findViewById<ImageView>(R.id.img_thumbnail_url))
                .load(photos.thumbnailUrl)
                .into(itemView.findViewById<ImageView>(R.id.img_thumbnail_url))
            itemView.findViewById<AppCompatTextView>(R.id.tv_title).text = photos.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.adapter_photos, parent,
            false
        )
    )

    override fun getItemCount() = photos.size


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    fun addData(list: Photos) {
        photos.addAll(listOf(list) )
    }
}