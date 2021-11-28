package com.jedun.cleanpixabay.data.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jedun.cleanpixabay.data.cache.model.HitCrossRefTag
import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.data.cache.model.HitsWithTag
import com.jedun.cleanpixabay.data.cache.model.TagEntity
import io.reactivex.Flowable

@Dao
abstract class ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertImages(images: HitEntity, tag: List<TagEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertImageTagCrossRef(hitCrossRefTag: HitCrossRefTag)

    fun insertImagesAggregate(images: List<HitsWithTag>) {
        for (image in images) {
            insertImages(image.hits, image.tags)
            for (tag in image.tags) {
                insertImageTagCrossRef(HitCrossRefTag(image.hits.id, tag.tag))
            }
        }
    }

    @Transaction
    @Query("SELECT * FROM images  WHERE `query` LIKE '%' || :query || '%'")
    abstract fun getAggregateImages(query: String): Flowable<List<HitsWithTag>>
}