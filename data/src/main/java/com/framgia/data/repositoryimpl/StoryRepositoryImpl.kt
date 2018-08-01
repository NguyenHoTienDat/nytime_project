package com.framgia.data.repositoryimpl

import com.framgia.data.di.module.NetworkModule
import com.framgia.data.model.StoryEntityMapper
import com.framgia.data.remote.api.StoryApi
import com.framgia.domain.model.Story
import com.framgia.domain.repository.StoryRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class StoryRepositoryImpl @Inject constructor(
        private val mStoryApi: StoryApi,
        private val mMapper: StoryEntityMapper
) : StoryRepository {

    override fun getTopStories(type: String): Single<List<Story>> {
        return mStoryApi.getTopStories(type).map {
            it.results.map { mMapper.mapToDomain(it) }
        }
    }
}
