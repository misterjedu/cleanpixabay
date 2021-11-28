package com.jedun.cleanpixabay.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jedun.cleanpixabay.data.cache.ListConverter
import com.jedun.cleanpixabay.data.cache.model.HitCrossRefTag
import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.data.cache.model.TagEntity

@Database(
    entities = [
        HitEntity::class, HitCrossRefTag::class, TagEntity::class],
    version = 1, exportSchema = false
)
abstract class PixaBayImageDatabase : RoomDatabase() {

    abstract val pixaBayImageDao: ImageDao

    @TypeConverters(
        ListConverter::class
    )
    companion object {
        val DATABASE_NAME: String = "pixabay_images_db"
    }
}