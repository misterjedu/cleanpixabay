package com.jedun.cleanpixabay.data.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
   @PrimaryKey val tag: String
)
