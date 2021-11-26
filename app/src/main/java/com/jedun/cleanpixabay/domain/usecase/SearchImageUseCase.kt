package com.jedun.cleanpixabay.domain.usecase

import com.jedun.cleanpixabay.domain.mapper.HitDomainMapper
import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.domain.model.PixabayRequest
import com.jedun.cleanpixabay.domain.repository.ImageRepository
import com.jedun.cleanpixabay.utils.Resource
import io.reactivex.Observable
import javax.inject.Inject


class SearchImageUseCase @Inject constructor(
    private val repository: ImageRepository,
    var entityMapper: HitDomainMapper
) {

    operator fun invoke(pixabayRequest: PixabayRequest):
            Observable<Resource<List<Hit>>> {

        return repository.searchImages(pixabayRequest)
            .map {
                when (it) {
                    is Resource.Error -> {
                        it.message?.let { it1 -> Resource.Error(it1) }
                    }
                    is Resource.Loading -> {
                        Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (it.data.isNullOrEmpty()) {
                            Resource.Error("No results found in cache")
                        } else {
                            Resource.Success(it.data.map { hitEntity ->
                                entityMapper.mapFromEntity(hitEntity)
                            })
                        }
                    }
                }
            }
    }
}