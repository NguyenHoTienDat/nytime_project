package com.framgia.data.di.module

import android.app.Application
import com.framgia.data.local.database.NewsDao
import com.framgia.data.local.database.NyTimeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideNyTimeLocalDao(application: Application): NewsDao =
            NyTimeDatabase.newInstance(application).localNyTimeDao()
}
