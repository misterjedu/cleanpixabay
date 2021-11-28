package com.jedun.cleanpixabay.data.cache.mapper

import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.data.cache.model.HitsWithTag
import com.jedun.cleanpixabay.data.cache.model.TagEntity
import com.jedun.cleanpixabay.data.network.model.HitDto
import com.jedun.cleanpixabay.data.util.iCacheMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : iCacheMapper<HitDto, HitsWithTag> {

    override fun mapToCache(dto: HitDto, query: String): HitsWithTag {
        return HitsWithTag(
            mapHitEntity(dto, query),
            dto.tags?.split(",")?.map { TagEntity(it) }.orEmpty()
        )
    }

    private fun mapHitEntity(dto: HitDto, query: String): HitEntity {
        return HitEntity(
            id = dto.id,
            previewURL = dto.previewURL,
            largeImageURL = dto.largeImageURL,
            user = dto.user,
            likes = dto.likes,
            downloads = dto.downloads,
            comments = dto.comments,
            query = query
        )
    }

}
