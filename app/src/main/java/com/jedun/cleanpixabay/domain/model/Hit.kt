package com.jedun.cleanpixabay.domain.model

data class Hit(
    val id: Int,
    val previewURL: String? = null,
    val largeImageURL: String? = null,
    val user: String? = null,
    val likes: Int? = null,
    val downloads: Int? = null,
    val comments: Int? = null,
    val tags: List<String>? = null,
)
