package com.jedun.cleanpixabay.data.cache.mapper

import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.data.network.model.HitDto
import com.jedun.cleanpixabay.domain.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<HitEntity, HitDto> {

    override fun mapFromEntity(entity: HitEntity): HitDto {
        return HitDto(
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

    override fun mapToEntity(dto: HitDto): HitEntity {

        return HitEntity(
            id = dto.id,
            previewURL = dto.previewURL,
            largeImageURL = dto.largeImageURL,
            user = dto.user,
            likes = dto.likes,
            downloads = dto.downloads,
            comments = dto.comments,
            tags = dto.tags,
        )
    }

    fun mapToEntityWithQuery(dto: HitDto, query: String): HitEntity {
        return HitEntity(
            id = dto.id,
            previewURL = dto.previewURL,
            largeImageURL = dto.largeImageURL,
            user = dto.user,
            likes = dto.likes,
            downloads = dto.downloads,
            comments = dto.comments,
            tags = dto.tags,
            query = query
        )
    }

}
