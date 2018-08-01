package com.framgia.data.di.module

import com.framgia.data.BuildConfig
import com.framgia.data.remote.api.StoryApi
import com.framgia.data.remote.api.MovieApi
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
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

    @Provides
    @Singleton
    @Named(MOVIE_DB_CLIENT_NAME)
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("api_key", BuildConfig.MOVIE_API_KEY)
                .build()
            return@addInterceptor chain.proceed(request)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(@Named(MOVIE_DB_CLIENT_NAME) okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Provides
    @Singleton
    @Named(BASE_TOP_STORIES_URL)
    fun provideNyTimeRetrofit(builder : Retrofit.Builder): Retrofit
            = builder.baseUrl(BASE_TOP_STORIES_URL).build()

    @Provides
    @Singleton
    fun provideStoryApi(@Named(BASE_TOP_STORIES_URL) retrofit: Retrofit): StoryApi
            = retrofit.create(StoryApi::class.java)

    @Provides
    @Singleton
    @Named(API_KEY_NYTIME_NAMED)
    fun provideApiKey() = BuildConfig.API_NYTIME_KEY

    @Named(MOVIE_DB_RETROFIT_NAME)
    fun provideMovieRetrofit(builder: Retrofit.Builder): Retrofit =
        builder.baseUrl(MovieApi.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(@Named(MOVIE_DB_RETROFIT_NAME) retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    companion object {

        const val BASE_TOP_STORIES_URL = "https://api.nytimes.com/svc/topstories/v2/"

        const val CONNECTION_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L

        const val API_KEY_NYTIME_NAMED = "api_key_nytime"
        const val MOVIE_DB_CLIENT_NAME = "movie_db_client"
        const val MOVIE_DB_RETROFIT_NAME = "movie_db"
    }
}
