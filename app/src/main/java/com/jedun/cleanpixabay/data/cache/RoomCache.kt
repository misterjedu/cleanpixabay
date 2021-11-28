package com.jedun.cleanpixabay.data.cache

import com.jedun.cleanpixabay.data.cache.database.ImageDao
import com.jedun.cleanpixabay.data.cache.model.HitsWithTag
import io.reactivex.Flowable
import javax.inject.Inject

class RoomCache @Inject constructor(private val imageDao: ImageDao) : Cache {

    override fun insertImages(images: List<HitsWithTag>) {
        imageDao.insertImagesAggregate(images)
    }

    override fun getImages(query: String, page: Int): Flowable<List<HitsWithTag>> {
        return imageDao.getAggregateImages(query)
    }

}