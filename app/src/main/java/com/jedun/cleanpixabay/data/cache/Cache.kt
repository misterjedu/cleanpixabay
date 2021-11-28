package com.jedun.cleanpixabay.data.cache

import com.jedun.cleanpixabay.data.cache.model.HitsWithTag
import io.reactivex.Flowable

interface Cache {

    fun insertImages(images: List<HitsWithTag>)

    fun getImages(query: String, page: Int): Flowable<List<HitsWithTag>>

}