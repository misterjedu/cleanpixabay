package com.jedun.cleanpixabay.domain.mapper

import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.domain.util.EntityMapper
import com.jedun.cleanpixabay.utils.Resource
import javax.inject.Inject


class ResourceDomainMapper @Inject constructor(private val hitDomainMapper: HitDomainMapper) :
    EntityMapper<Resource<HitEntity>, Resource<Hit>> {
    override fun mapFromEntity(entity: Resource<HitEntity>): Resource<Hit> {

        return when (entity) {
            is Resource.Error -> {
                return Resource.Error(entity.message!!)
            }
            is Resource.Loading -> {
                return Resource.Loading()
            }
            is Resource.Success -> Resource.Success(entity.data!!.let {
                hitDomainMapper.mapFromEntity(it)
            })
        }

    }

    override fun mapToEntity(domainModel: Resource<Hit>): Resource<HitEntity> {

        return when (domainModel) {
            is Resource.Error -> {
                return Resource.Error(domainModel.message!!)
            }
            is Resource.Loading -> {
                return Resource.Loading()
            }
            is Resource.Success -> Resource.Success(domainModel.data!!.let {
                hitDomainMapper.mapToEntity(it)
            })
        }

    }
}