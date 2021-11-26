package com.jedun.cleanpixabay.presentation.util

interface UiMapper<Ui, DomainModel> {


    fun mapToDomain(domainModel: DomainModel): Ui
}