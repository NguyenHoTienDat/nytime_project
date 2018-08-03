package com.framgia.data.di.module

import android.util.Log
import com.framgia.data.BuildConfig
import com.framgia.data.remote.api.MovieApi
import com.framgia.data.remote.api.StoryApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

    @Provides
    @Singleton
    @Named(BASE_TOP_STORIES_URL)
    fun provideNyTimeOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("api-key", BuildConfig.API_NYTIME_KEY)
                .build()
            return@addInterceptor chain.proceed(request)
        }.build()

    @Provides
    @Singleton
    @Named(BASE_TOP_STORIES_URL)
    fun provideNyTimeRetrofitBuilder(@Named(BASE_TOP_STORIES_URL) okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @Singleton
    @Named(BASE_TOP_STORIES_URL)
    fun provideNyTimeRetrofit(@Named(BASE_TOP_STORIES_URL) builder: Retrofit.Builder): Retrofit =
        builder.baseUrl(BASE_TOP_STORIES_URL).build()

    @Provides
    @Singleton
    fun provideStoryApi(@Named(BASE_TOP_STORIES_URL) retrofit: Retrofit): StoryApi =
        retrofit.create(StoryApi::class.java)

    @Provides
    @Singleton
    @Named(MOVIE_DB_NAME)
    fun provideMovieOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("api_key", BuildConfig.MOVIE_API_KEY)
                .build()
            return@addInterceptor chain.proceed(request)
        }.build()

    @Provides
    @Singleton
    @Named(MOVIE_DB_NAME)
    fun provideMovieRetrofitBuilder(@Named(MOVIE_DB_NAME) okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @Named(MOVIE_DB_NAME)
    fun provideMovieRetrofit(@Named(MOVIE_DB_NAME) builder: Retrofit.Builder): Retrofit =
        builder.baseUrl(MovieApi.BASE_URL).build()

    @Provides
    @Singleton
    fun provideMovieApi(@Named(MOVIE_DB_NAME) retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    companion object {

        const val BASE_TOP_STORIES_URL = "https://api.nytimes.com/svc/topstories/v2/"

        const val CONNECTION_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L

        const val MOVIE_DB_NAME = "movie_db"
    }
}
