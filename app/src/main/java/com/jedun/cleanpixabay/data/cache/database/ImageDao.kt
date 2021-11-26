package com.jedun.cleanpixabay.data.cache.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jedun.cleanpixabay.data.cache.model.HitEntity
import io.reactivex.Flowable

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(images: List<HitEntity>)

    @Query("SELECT * FROM images  WHERE `query` LIKE '%' || :query || '%' LIMIT(:page * 20)")
    fun getImages(query: String, page: Int): Flowable<List<HitEntity>>

}