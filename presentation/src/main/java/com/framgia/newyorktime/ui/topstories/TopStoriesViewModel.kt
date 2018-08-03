package com.framgia.newyorktime.ui.topstories

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.framgia.domain.usecase.GetStoriesUseCase
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.viewmodel.BaseViewModel
import com.framgia.newyorktime.model.nytime.StoryGenreItem
import com.framgia.newyorktime.model.nytime.StoryItem
import com.framgia.newyorktime.model.nytime.StoryItemMapper
import com.framgia.newyorktime.rx.SchedulerProvider
import com.framgia.newyorktime.util.SharedPreUtils
import javax.inject.Inject


class TopStoriesViewModel @Inject constructor(
        private val application: Application,
        private val topStoriesUseCase: GetStoriesUseCase,
        private val itemMapper: StoryItemMapper,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val stories = MutableLiveData<List<StoryItem>>()
    val isDataLoading = MutableLiveData<Boolean>()
    var curGenres = generateGenres()

    private var curStoryType: String? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startViewModel() {
        getTopStories()
    }

    fun getTopStories(storyType: String? = null, isSwipe: Boolean = false) {
        if (curStoryType != null && storyType == curStoryType && !isSwipe) return
        if (!isSwipe) makeLoadingState(true)
        compositeDisposable.add(topStoriesUseCase.createObservable(
                GetStoriesUseCase.Params(storyType ?: SharedPreUtils.getStoryType(application)
                ))
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map { it.map { itemMapper.mapToPresentation(it) } }
                .subscribe({
                    stories.value = it
                    curStoryType = storyType ?: SharedPreUtils.getStoryType(application)
                }, {})
        )
    }

    fun makeLoadingState(state: Boolean) {
        isDataLoading.value = state
    }

    fun generateGenres() = mutableListOf<StoryGenreItem>().apply {
        add(StoryGenreItem(application.getString(R.string.story_section_home), R.drawable.selector_story_home))
        add(StoryGenreItem(application.getString(R.string.story_section_health), R.drawable.selector_story_health))
        add(StoryGenreItem(application.getString(R.string.story_section_arts), R.drawable.selector_story_arts))
        add(StoryGenreItem(application.getString(R.string.story_section_automobiles), R.drawable.selector_story_automobile))
        add(StoryGenreItem(application.getString(R.string.story_section_business), R.drawable.selector_story_business))
        add(StoryGenreItem(application.getString(R.string.story_section_books), R.drawable.selector_story_book))
        add(StoryGenreItem(application.getString(R.string.story_section_fashion), R.drawable.selector_story_fashion))
        add(StoryGenreItem(application.getString(R.string.story_section_movies), R.drawable.selector_story_movie))
        add(StoryGenreItem(application.getString(R.string.story_section_magazine), R.drawable.selector_story_magazine))
        add(StoryGenreItem(application.getString(R.string.story_section_travel), R.drawable.selector_story_travel))
        add(StoryGenreItem(application.getString(R.string.story_section_technology), R.drawable.selector_story_tech))
        add(StoryGenreItem(application.getString(R.string.story_section_sports), R.drawable.selector_story_sports))
        add(StoryGenreItem(application.getString(R.string.story_section_science), R.drawable.selector_story_science))
        add(StoryGenreItem(application.getString(R.string.story_section_world), R.drawable.selector_story_world))
    }
}
