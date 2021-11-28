package com.jedun.cleanpixabay.domain.util

interface iDomainMapper<Entity, DomainModel> {

    fun mapToDomain(entity: Entity): DomainModel

}