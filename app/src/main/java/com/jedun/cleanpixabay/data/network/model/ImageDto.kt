package com.jedun.cleanpixabay.data.network.model

import com.jedun.cleanpixabay.data.network.model.HitDto

data class ImageDto(
    val hits: List<HitDto>? = null,
    val total: Int? = null,
    val totalHits: Int? = null
)