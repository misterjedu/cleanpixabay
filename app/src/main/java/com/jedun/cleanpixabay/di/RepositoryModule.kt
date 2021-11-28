package com.jedun.cleanpixabay.di

import com.jedun.cleanpixabay.data.cache.Cache
import com.jedun.cleanpixabay.data.repository.ImageRepositoryImpl
import com.jedun.cleanpixabay.data.cache.database.ImageDao
import com.jedun.cleanpixabay.data.cache.database.PixaBayImageDatabase
import com.jedun.cleanpixabay.data.cache.mapper.CacheMapper
import com.jedun.cleanpixabay.data.network.PixaBayApi
import com.jedun.cleanpixabay.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDao(database: PixaBayImageDatabase): ImageDao {
        return database.pixaBayImageDao
    }

    @Provides
    @Singleton
    fun provideImageRepository(
        pixaBayApi: PixaBayApi, cache: Cache, cacheMapper: CacheMapper
    ): ImageRepository {
        return ImageRepositoryImpl(
            pixaBayApi,
            cache,
            cacheMapper
        )
    }

}