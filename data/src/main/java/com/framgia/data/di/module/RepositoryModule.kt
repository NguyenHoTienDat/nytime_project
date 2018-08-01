package com.framgia.data.di.module

import com.framgia.data.repositoryimpl.StoryRepositoryImpl
import com.framgia.domain.repository.StoryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providerStoryRepository(repository: StoryRepositoryImpl): StoryRepository {
        return repository
    }
}
