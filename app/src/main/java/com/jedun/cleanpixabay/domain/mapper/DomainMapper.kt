package com.jedun.cleanpixabay.domain.mapper

import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.data.cache.model.ImageEntity
import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.domain.model.Image
import com.jedun.cleanpixabay.domain.util.EntityMapper
import javax.inject.Inject

class DomainMapper @Inject constructor() : EntityMapper<ImageEntity, Image> {

    override fun mapFromEntity(entity: ImageEntity): Image {
        val hits: MutableList<Hit> = mutableListOf()

        for (hit in entity.hits) {
            hits.add(
                Hit(
                    id = hit.id,
                    previewURL = hit.previewURL,
                    largeImageURL = hit.largeImageURL,
                    user = hit.user,
                    likes = hit.likes,
                    downloads = hit.downloads,
                    comments = hit.comments,
                    tags = hit.tags
                )
            )
        }

        return Image(hits = hits)
    }

    override fun mapToEntity(domainModel: Image): ImageEntity {

        val hitEntities: MutableList<HitEntity> = mutableListOf()

        for (hit in domainModel.hits) {
            hitEntities.add(
                HitEntity(
                    id = hit.id,
                    previewURL = hit.previewURL,
                    largeImageURL = hit.largeImageURL,
                    user = hit.user,
                    likes = hit.likes,
                    downloads = hit.downloads,
                    comments = hit.comments,
                    tags = hit.tags,
                )
            )
        }

        return ImageEntity(hits = hitEntities)
    }
}
