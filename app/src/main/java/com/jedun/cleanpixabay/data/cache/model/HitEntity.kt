package com.jedun.cleanpixabay.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class HitEntity(
    @PrimaryKey val id: Int,
    val previewURL: String? = null,
    val largeImageURL: String? = null,
    val user: String? = null,
    val likes: Int? = null,
    val downloads: Int? = null,
    val comments: Int? = null,
    val tags: String? = null,
    var query: String? = null
)