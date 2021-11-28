package com.jedun.cleanpixabay.data.cache.model

import androidx.room.Entity

@Entity(primaryKeys = ["id", "tag"])
data class HitCrossRefTag(
    var id: Int,
    val tag: String
)
