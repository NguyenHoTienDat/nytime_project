package com.framgia.newyorktime.ui.main.mostpopularstories

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.framgia.domain.usecase.GetViewPopularUsecase
import com.framgia.newyorktime.base.viewmodel.BaseViewModel
import com.framgia.newyorktime.model.nytime.PopularItem
import com.framgia.newyorktime.model.nytime.PopularItemMapper
import com.framgia.newyorktime.rx.SchedulerProvider
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created: 03/08/2018
 * By: Sang
 * Description:
 */
class MostPopularStoriesViewModel @Inject constructor(
        private val application: Application,
        private val viewPopularUseCase: GetViewPopularUsecase,
        private val itemMapper: PopularItemMapper,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    val popular = MutableLiveData<List<PopularItem>>()
    val isDataLoading = MutableLiveData<Boolean>()
    val connectFailed = MutableLiveData<Boolean>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startViewModel() {
        makeLoadingState(true)
        getMostPopular()
    }

    fun getMostPopular(isSwipe: Boolean = false) {
        compositeDisposable.add(viewPopularUseCase.createObservable(
                GetViewPopularUsecase.Params())
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map { it.map { itemMapper.mapToPresentation(it) } }
                .subscribeBy(
                        onSuccess = {
                            popular.value = it
                            makeConnectFailedState(false)
                        },
                        onError = {
                            makeConnectFailedState(true)
                        }
                )
        )
    }

    fun makeLoadingState(state: Boolean) {
        isDataLoading.value = state
    }

    fun makeConnectFailedState(state: Boolean) {
        connectFailed.value = state
    }
}
