package com.jedun.cleanpixabay.presentation.ui.searchimages.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jedun.cleanpixabay.databinding.ItemPixabayImagesBinding
import com.jedun.cleanpixabay.presentation.model.HitPreview
import com.jedun.cleanpixabay.utils.Callback

class ImagePreviewItemViewHolder(private val binding: ItemPixabayImagesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    lateinit var context: Context
    lateinit var chipGroup: ChipGroup

    fun bind(hitPreview: HitPreview, clickListener: Callback<Int>) {

        binding.apply {
            chipGroup = imageTagChipGroup
            context = this.root.context
            root.setOnClickListener {
                clickListener(hitPreview.id)
            }
            this.hitPreview = hitPreview
        }

        chipGroup.removeAllViews()
        hitPreview.tagList.forEach { addChip(it.trim()) }
    }

    private fun addChip(text: String) {
        val chip = Chip(context)
        chip.text = text
        chipGroup.addView(chip)
    }

}