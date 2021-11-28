package com.jedun.cleanpixabay.data.cache.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class HitsWithTag(
    @Embedded val hits: HitEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "tag",
        associateBy = Junction(HitCrossRefTag::class)
    )
    val tags: List<TagEntity>
)
