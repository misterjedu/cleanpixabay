package com.jedun.cleanpixabay.data.repository

import com.jedun.cleanpixabay.data.cache.Cache
import com.jedun.cleanpixabay.data.cache.mapper.CacheMapper
import com.jedun.cleanpixabay.data.cache.model.HitsWithTag
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
    private val cache: Cache,
    var entityMapper: CacheMapper
) : ImageRepository {

    override fun searchImages(pixabayRequest: PixabayRequest):
            Observable<Resource<List<HitsWithTag>>> {

        pixaBayApi.retrievePhotos(query = pixabayRequest.query, page = pixabayRequest.page)
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ImageDto> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(imageDto: ImageDto) {
                    imageDto.hits?.map {
                        entityMapper.mapToCache(
                            it,
                            pixabayRequest.query
                        )
                    }
                        ?.let { cache.insertImages(it) }
                }

                override fun onError(e: Throwable) {
                    println("repository $e.message")
                }
            })

        return cache.getImages(pixabayRequest.query, pixabayRequest.page)
            .toObservable()
            .map { hitWithTag ->
                try {
                    println("Image Repository" + hitWithTag)
                    Resource.Success(hitWithTag)
                } catch (t: Throwable) {
                    t.message?.let { Resource.Error(it) }
                }
            }
    }
}
