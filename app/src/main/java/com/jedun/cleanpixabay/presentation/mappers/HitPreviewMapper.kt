package com.jedun.cleanpixabay.presentation.mappers

import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.presentation.model.HitPreview
import com.jedun.cleanpixabay.presentation.util.UiMapper
import javax.inject.Inject

class HitPreviewMapper @Inject constructor() : UiMapper<HitPreview, Hit> {
    override fun mapToDomain(domainModel: Hit): HitPreview {

        return HitPreview(
            id = domainModel.id,
            thumbNail = domainModel.previewURL ?: "",
            name = domainModel.user ?: "",
            tagList = domainModel.tags ?: ""

        )
    }


}