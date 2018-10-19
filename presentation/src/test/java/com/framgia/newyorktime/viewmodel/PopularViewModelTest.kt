package com.framgia.newyorktime.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.framgia.domain.model.MostPopular
import com.framgia.domain.usecase.*
import com.framgia.newyorktime.RxSchedulersOverrideRule
import com.framgia.newyorktime.model.nytime.*
import com.framgia.newyorktime.rx.AppSchedulerProvider
import com.framgia.newyorktime.rx.SchedulerProvider
import com.framgia.newyorktime.ui.main.mostpopularstories.MostPopularStoriesViewModel
import com.framgia.newyorktime.ui.topstories.TopStoriesViewModel
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class PopularViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxSchedulersOverrideRule: RxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var viewPopularUseCase: GetViewPopularUsecase

    @Mock
    private lateinit var savePopularLocalUseCase: SavePopularLocalUseCase

    @Mock
    private lateinit var unSavePopularLocalUseCase: UnSavePopularLocalUseCase

    @Mock
    private lateinit var findExistLocalPopularUseCase: FindExistLocalPopularUseCase

    @Spy
    private lateinit var popularLocalMapper: PopularLocalMapper

    @Spy
    private lateinit var itemMapper: PopularItemMapper

    private var schedulerProvider: SchedulerProvider = AppSchedulerProvider()

    private lateinit var vm: MostPopularStoriesViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        schedulerProvider = AppSchedulerProvider()

        vm = MostPopularStoriesViewModel(viewPopularUseCase, savePopularLocalUseCase,
                unSavePopularLocalUseCase, findExistLocalPopularUseCase,
                popularLocalMapper, itemMapper, schedulerProvider)
    }

    @Test
    fun testCallCheckDataBaseExist() {
//        `when`(viewPopularUseCase.createObservable(ArgumentMatchers.any(Void::class.java)))
//                .thenReturn(Single.just(listOf(createMostPopular())))
//
//        vm.getMostPopular()

    }

    private fun createMostPopular() = MostPopular("My Title Item", "My Abs Item",
            "http://Item", "abcItem", "http://imageItem")
}