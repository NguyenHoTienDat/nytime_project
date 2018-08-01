package com.framgia.domain.repository

import com.framgia.domain.model.Story
import io.reactivex.Single

interface StoryRepository {
    fun getTopStories(type: String): Single<List<Story>>
}
