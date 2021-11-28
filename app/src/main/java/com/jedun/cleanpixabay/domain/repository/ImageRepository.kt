package com.jedun.cleanpixabay.domain.repository

import com.jedun.cleanpixabay.data.cache.model.HitsWithTag
import com.jedun.cleanpixabay.domain.model.PixabayRequest
import com.jedun.cleanpixabay.utils.Resource
import io.reactivex.Observable

interface ImageRepository {

    fun searchImages(pixabayRequest: PixabayRequest):
            Observable<Resource<List<HitsWithTag>>>

}