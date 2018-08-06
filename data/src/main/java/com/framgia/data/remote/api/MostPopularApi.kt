package com.framgia.data.remote.api

import com.framgia.data.model.MostPopularEntity
import com.framgia.data.remote.response.StoryWrapperResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MostPopularApi {
    @GET("mostemailed/all-sections/1.json")
    fun getMostEmailPopular() : Single<StoryWrapperResponse<MostPopularEntity>>

    @GET("/mostshared/business/facebook;twitter/7.json")
    fun getMostViewPopular() : Single<StoryWrapperResponse<MostPopularEntity>>

    @GET("mostviewed/arts/30.json")
    fun getMostSharePopular() : Single<StoryWrapperResponse<MostPopularEntity>>
}
