package com.framgia.newyorktime.base.viewmodel

import android.arch.lifecycle.ViewModel
import com.framgia.domain.base.UseCase
import io.reactivex.disposables.CompositeDisposable

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
class BaseViewModel(private vararg val useCases: UseCase<*, *>) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        if (useCases.isNotEmpty()) {
            useCases.forEach { it.onCleared() }
        }
        super.onCleared()
    }
}