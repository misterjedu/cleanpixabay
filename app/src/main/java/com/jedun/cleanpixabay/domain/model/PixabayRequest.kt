package com.jedun.cleanpixabay.domain.model

import com.jedun.cleanpixabay.utils.IMAGE_TYPE

data class PixabayRequest(
    var query: String,
    var page: Int,
    var imageType: String = IMAGE_TYPE
)
