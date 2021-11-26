package com.jedun.cleanpixabay.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jedun.cleanpixabay.data.cache.ListConverter
import com.jedun.cleanpixabay.data.cache.model.HitEntity


@Database(entities = [HitEntity::class], version = 1)
abstract class PixaBayImageDatabase : RoomDatabase() {

    abstract val pixaBayImageDao: ImageDao

    @TypeConverters(
        ListConverter::class
    )
    companion object {
        val DATABASE_NAME: String = "pixabay_images_db"
    }
}