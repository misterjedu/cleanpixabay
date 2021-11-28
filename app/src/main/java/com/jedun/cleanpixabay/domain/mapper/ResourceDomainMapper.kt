package com.jedun.cleanpixabay.domain.mapper

import com.jedun.cleanpixabay.data.cache.model.HitEntity
import com.jedun.cleanpixabay.domain.model.Hit
import com.jedun.cleanpixabay.domain.util.iDomainMapper
import com.jedun.cleanpixabay.utils.Resource
import javax.inject.Inject


//class ResourceDomainMapper @Inject constructor(private val hitDomainMapper: HitDomainMapper) :
//    iDomainMapper<Resource<HitEntity>, Resource<Hit>> {
//    override fun mapToDomain(entity: Resource<HitEntity>): Resource<Hit> {
//
//        return when (entity) {
//            is Resource.Error -> {
//                return Resource.Error(entity.message!!)
//            }
//            is Resource.Loading -> {
//                return Resource.Loading()
//            }
//            is Resource.Success -> Resource.Success(entity.data!!.let {
//                hitDomainMapper.mapToDomain(it)
//            })
//        }
//
//    }
//}