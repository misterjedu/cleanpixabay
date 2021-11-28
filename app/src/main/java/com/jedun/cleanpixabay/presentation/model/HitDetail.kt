package com.jedun.cleanpixabay.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HitDetail(
    var id: Int,
    var image: String,
    var name: String,
    var tagList: List<String>,
    var likes: Int,
    var downloads: Int,
    var comments: Int
) : Parcelable
