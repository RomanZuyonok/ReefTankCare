package com.reeftankcare.ui.photo_base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reeftankcare.databinding.ItemGalleryBinding
import com.reeftankcare.network.GalleryItem

class PhotoBaseAdapter(
    private val galleryItems: List<GalleryItem>
) : RecyclerView.Adapter<PhotoBaseViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoBaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemGalleryBinding.inflate(inflater,parent, false)
        return PhotoBaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoBaseViewHolder, position: Int) {
        val item = galleryItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = galleryItems.size
}