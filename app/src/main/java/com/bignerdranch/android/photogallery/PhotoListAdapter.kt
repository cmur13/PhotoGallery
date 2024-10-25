package com.bignerdranch.android.photogallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import api.GalleryItem
import coil.load
import com.bignerdranch.android.photogallery.databinding.ListItemGalleryBinding

class PhotoViewHolder(
    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    //23.2 added ", onItemClicked: (Uri) -> Unit) {"
    fun bind(galleryItem: GalleryItem, onItemClicked: (Uri) -> Unit) {
        binding.itemImageView.load(galleryItem.url){
            placeholder(R.drawable.bill_up_close)
        }
        //23.2 added  binding.root.setOnClickListener { onItemClicked(galleryItem.photoPageUri) }
        binding.root.setOnClickListener { onItemClicked(galleryItem.photoPageUri) }
    }
}

class PhotoListAdapter(
    private val galleryItems: List<GalleryItem>,

    //23.3 added comma above too
    private val onItemClicked: (Uri) -> Unit

) : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = galleryItems[position]

        //23.3 added onItemClicked
        holder.bind(item, onItemClicked)
    }
    override fun getItemCount() = galleryItems.size
}