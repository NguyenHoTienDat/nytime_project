package com.framgia.newyorktime.ui.main

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.framgia.newyorktime.base.viewmodel.BaseViewModel
import com.framgia.newyorktime.util.custom.SingleLiveEvent
import javax.inject.Inject

/**
 * Created by fs-sournary.
 * Date: 8/2/18.
 * Description:
 */
class MainViewModel @Inject constructor() : BaseViewModel() {

    val openPageEvent = MutableLiveData<Int>()
    val openSearchEvent = SingleLiveEvent<Void>()
    val openOfflineNewsEvent = SingleLiveEvent<Void>()
    val openOfflineMoviesEvent = SingleLiveEvent<Void>()

    fun loadSearch() {
        openSearchEvent.call()
    }

    fun loadOfflineNews() {
        openOfflineNewsEvent.call()
    }

    fun loadOfflineMovies() {
        openOfflineMoviesEvent.call()
    }
}
