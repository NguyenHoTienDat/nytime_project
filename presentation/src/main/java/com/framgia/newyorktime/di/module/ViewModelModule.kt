package com.framgia.newyorktime.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.framgia.newyorktime.ViewModelFactory
import com.framgia.newyorktime.ui.topstories.TopStoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TopStoriesViewModel::class)
    abstract fun bindMainViewModel(topStoriesViewModel: TopStoriesViewModel): ViewModel
}
