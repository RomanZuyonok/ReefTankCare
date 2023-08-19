package com.reeftankcare.ui.photo_base

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.reeftankcare.R
import com.reeftankcare.databinding.ItemGalleryBinding
import com.reeftankcare.network.GalleryItem

class PhotoBaseViewHolder(
    private val binding: ItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(galleryItem: GalleryItem){
        binding.itemImageView.load(galleryItem.url){
            placeholder(R.drawable.bill_up_close)
        }
    }
}