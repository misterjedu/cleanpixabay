package com.jedun.cleanpixabay.data.util

interface iCacheMapper<RemoteModel, CacheModel> {

    fun mapToCache(dto: RemoteModel, query: String): CacheModel

}