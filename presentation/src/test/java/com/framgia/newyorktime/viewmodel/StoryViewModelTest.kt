package com.framgia.newyorktime.viewmodel

import android.app.Application
import com.framgia.domain.usecase.*
import com.framgia.newyorktime.model.nytime.StoryItem
import com.framgia.newyorktime.model.nytime.StoryItemMapper
import com.framgia.newyorktime.model.nytime.StoryLocalMapper
import com.framgia.newyorktime.rx.SchedulerProvider
import com.framgia.newyorktime.ui.topstories.TopStoriesViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryViewModelTest {

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var topStoriesUseCase: GetStoriesUseCase

    @Mock
    private lateinit var getStoryLocalUseCase: GetStoryLocalUseCase

    @Mock
    private lateinit var saveStoryLocalUseCase: SaveStoryLocalUseCase

    @Mock
    private lateinit var unSaveStoryLocalUseCase: UnSaveStoryLocalUseCase

    @Mock
    private lateinit var findExistLocalStoryUseCase: FindExistLocalStoryUseCase

    @Spy
    private lateinit var itemMapper: StoryItemMapper

    @Spy
    private lateinit var storyLocalMapper: StoryLocalMapper

    @Spy
    private lateinit var schedulerProvider: SchedulerProvider

    @Mock
    private lateinit var vm: TopStoriesViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testCallCheckDataBaseExist() {
        //`when`(vm.getTopStories()).then { vm.checkStoryExist(listOf(createStoryItem()), null) }
        //Mockito.verify(vm).checkStoryExist(listOf(createStoryItem()), null)
    }

    private fun createStoryItem() = StoryItem("My Title Item", "My Abs Item",
            "http://Item", "abcItem", "http://imageItem", "18/10/2018")
}