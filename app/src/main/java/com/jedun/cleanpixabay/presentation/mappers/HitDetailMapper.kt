package com.jedun.cleanpixabay.presentation.mappers

import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.presentation.model.HitDetail
import com.jedun.cleanpixabay.presentation.util.UiMapper
import javax.inject.Inject

class HitDetailMapper @Inject constructor() : UiMapper<HitDetail, Hit> {

    override fun mapToDomain(domainModel: Hit): HitDetail {
        return HitDetail(
            id = domainModel.id,
            image = domainModel.largeImageURL ?: "",
            name = domainModel.user ?: "",
            tagList = domainModel.tags ?: emptyList(),
            likes = domainModel.likes ?: 0,
            downloads = domainModel.downloads ?: 0,
            comments = domainModel.comments ?: 0
        )
    }
}