package com.jedun.cleanpixabay.presentation.ui.searchimages.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jedun.cleanpixabay.databinding.ItemPixabayImagesBinding
import com.jedun.cleanpixabay.presentation.model.HitPreview
import com.jedun.cleanpixabay.utils.Callback

class HitPreviewListAdapter(private val clickListener: Callback<Int>) :
    ListAdapter<HitPreview, ViewHolder>(ImagePreviewItemDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPixabayImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagePreviewItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            (holder as ImagePreviewItemViewHolder).bind(currentItem, clickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    companion object {
        const val IMAGE_TYPE = 0
        const val LOADING_TYPE = 1
        const val EXHAUSTED_TYPE = 2
    }
}