package com.jedun.cleanpixabay.domain.mapper

import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.domain.util.EntityMapper
import javax.inject.Inject

class HitDomainMapper @Inject constructor() : EntityMapper<HitEntity, Hit> {
    override fun mapFromEntity(entity: HitEntity): Hit {
        return Hit(
            id = entity.id,
            previewURL = entity.previewURL,
            largeImageURL = entity.largeImageURL,
            user = entity.user,
            likes = entity.likes,
            downloads = entity.downloads,
            comments = entity.comments,
            tags = entity.tags
        )
    }

    override fun mapToEntity(domainModel: Hit): HitEntity {
        return HitEntity(
            id = domainModel.id,
            previewURL = domainModel.previewURL,
            largeImageURL = domainModel.largeImageURL,
            user = domainModel.user,
            likes = domainModel.likes,
            downloads = domainModel.downloads,
            comments = domainModel.comments,
            tags = domainModel.tags
        )
    }
}