package com.jedun.cleanpixabay.data.repository

import com.jedun.cleanpixabay.data.cache.database.ImageDao
import com.jedun.cleanpixabay.data.cache.mapper.CacheMapper
import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.data.network.PixaBayApi
import com.jedun.cleanpixabay.data.network.model.ImageDto
import com.jedun.cleanpixabay.domain.model.PixabayRequest
import com.jedun.cleanpixabay.domain.repository.ImageRepository
import com.jedun.cleanpixabay.utils.Resource
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val pixaBayApi: PixaBayApi,
    private val imageDao: ImageDao,
    var entityMapper: CacheMapper
) : ImageRepository {

    override fun searchImages(pixabayRequest: PixabayRequest):
            Observable<Resource<List<HitEntity>>> {

        pixaBayApi.retrievePhotos(query = pixabayRequest.query, page = pixabayRequest.page)
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ImageDto> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(imageDto: ImageDto) {
                    imageDto.hits?.map {
                        entityMapper.mapToEntityWithQuery(
                            it,
                            pixabayRequest.query
                        )
                    }
                        ?.let { imageDao.insertImages(it) }
                }

                override fun onError(e: Throwable) {
                    println("repository $e.message")
                }
            })

        return imageDao.getImages(pixabayRequest.query, pixabayRequest.page)
            .toObservable()
            .map { hitEntity ->
                try {
                    Resource.Success(hitEntity)
                } catch (t: Throwable) {
                    t.message?.let { Resource.Error(it) }
                }
            }

    }
}
