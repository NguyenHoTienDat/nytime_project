package com.framgia.domain.usecase

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
abstract class UseCase<in Params, out T> where T : Any {

    abstract fun createObservable(params: Params): T

    abstract fun onCleared()
}
