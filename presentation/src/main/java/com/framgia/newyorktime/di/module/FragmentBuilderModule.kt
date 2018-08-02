package com.framgia.newyorktime.di.module

import com.framgia.newyorktime.ui.topstories.TopStoriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeTopStoriesFragment(): TopStoriesFragment
}
