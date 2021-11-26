package com.jedun.cleanpixabay.data.repository

import com.jedun.cleanpixabay.data.BaseRepository
import com.jedun.cleanpixabay.data.cache.database.ImageDao
import com.jedun.cleanpixabay.data.cache.mapper.CacheMapper
import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.data.network.PixaBayApi
import com.jedun.cleanpixabay.domain.model.PixabayRequest
import com.jedun.cleanpixabay.domain.repository.ImageRepository
import com.jedun.cleanpixabay.utils.Resource
import io.reactivex.Observable
import javax.inject.Inject


class Repo @Inject constructor(
    private val pixaBayApi: PixaBayApi,
    private val imageDao: ImageDao,
    var entityMapper: CacheMapper
) : BaseRepository(), ImageRepository {

    override fun searchImages(pixabayRequest: PixabayRequest): Observable<Resource<List<HitEntity>>> {
        TODO("Not yet implemented")
//
//        perform(
//            imageDao.getImages(pixabayRequest.query, pixabayRequest.page),
//            pixaBayApi.retrievePhotos(query = pixabayRequest.query, page = pixabayRequest.page).flatMap {
//                it.hits.map{dto -> entityMapper.mapToEntity(dto)}
//            },
//        )
    }
}
