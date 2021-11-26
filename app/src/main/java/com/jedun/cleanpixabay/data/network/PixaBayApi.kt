package com.jedun.cleanpixabay.data.network

import com.jedun.cleanpixabay.data.network.NetworkConstants
import com.jedun.cleanpixabay.data.network.model.ImageDto
import com.jedun.cleanpixabay.utils.IMAGE_TYPE
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayApi {

    @GET("/api")
    fun retrievePhotos(
        @Query("key") key: String = NetworkConstants.APIKEY,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("image_type") imageType: String = IMAGE_TYPE
    ): Single<ImageDto>
}