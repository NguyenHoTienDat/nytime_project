package com.framgia.newyorktime.ui.topstories

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.framgia.domain.usecase.GetStoriesUseCase
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.viewmodel.BaseViewModel
import com.framgia.newyorktime.model.nytime.StoryItem
import com.framgia.newyorktime.model.nytime.StoryItemMapper
import com.framgia.newyorktime.rx.SchedulerProvider
import javax.inject.Inject


class TopStoriesViewModel @Inject constructor(
        private val application: Application,
        private val topStoriesUseCase: GetStoriesUseCase,
        private val itemMapper: StoryItemMapper,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val stories = MutableLiveData<List<StoryItem>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startViewModel() {
        compositeDisposable.add(topStoriesUseCase.createObservable(
                GetStoriesUseCase.Params(application.getString(R.string.story_section_home)))
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map { it.map { itemMapper.mapToPresentation(it) } }
                .subscribe({
                    stories.value = it
                }, {})
        )
    }
}
