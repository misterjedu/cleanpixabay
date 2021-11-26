package com.jedun.cleanpixabay.presentation.ui.searchimages.adapters

import androidx.recyclerview.widget.DiffUtil
import com.jedun.cleanpixabay.presentation.model.HitPreview

/**
 * Diff util to help optimize recycler view adapter
 */
class ImagePreviewItemDiffUtil : DiffUtil.ItemCallback<HitPreview>() {

    override fun areItemsTheSame(oldItem: HitPreview, newItem: HitPreview) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HitPreview, newItem: HitPreview) =
        oldItem == newItem
}