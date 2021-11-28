package com.jedun.cleanpixabay.domain.mapper

import com.jedun.cleanpixabay.data.cache.model.HitsWithTag
import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.domain.util.iDomainMapper
import javax.inject.Inject

class HitDomainMapper @Inject constructor() : iDomainMapper<HitsWithTag, Hit> {
    override fun mapToDomain(entity: HitsWithTag): Hit {
        return Hit(
            id = entity.hits.id,
            previewURL = entity.hits.previewURL,
            largeImageURL = entity.hits.largeImageURL,
            user = entity.hits.user,
            likes = entity.hits.likes,
            downloads = entity.hits.downloads,
            comments = entity.hits.comments,
            tags = entity.tags.map { it.tag }
        )
    }

}