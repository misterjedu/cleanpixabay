package com.jedun.cleanpixabay.di

import android.content.Context
import androidx.room.Room
import com.jedun.cleanpixabay.data.cache.Cache
import com.jedun.cleanpixabay.data.cache.RoomCache
import com.jedun.cleanpixabay.data.cache.database.PixaBayImageDatabase
import com.jedun.cleanpixabay.data.network.NetworkConstants
import com.jedun.cleanpixabay.data.network.PixaBayApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun providePixaBayDatabase(@ApplicationContext context: Context): PixaBayImageDatabase {
            return Room.databaseBuilder(
                context,
                PixaBayImageDatabase::class.java,
                PixaBayImageDatabase.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }


        @Provides
        @Singleton
        fun provideLogger(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        @Provides
        @Singleton
        fun provideClient(
            logger: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()
        }

        @Provides
        @Singleton
        fun provideConverterFactory(): Converter.Factory {
            return GsonConverterFactory.create()
        }

        @Provides
        @Singleton
        fun provideRetrofitClient(
            client: OkHttpClient,
            converterFactory: Converter.Factory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(NetworkConstants.BASEURL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }

        @Provides
        @Singleton
        fun providePixaBayService(retrofit: Retrofit): PixaBayApi {
            return retrofit.create(PixaBayApi::class.java)
        }
    }

}