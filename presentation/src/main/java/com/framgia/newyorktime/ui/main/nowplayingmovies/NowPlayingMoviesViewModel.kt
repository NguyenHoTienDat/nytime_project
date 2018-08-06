package com.framgia.newyorktime.ui.main.nowplayingmovies

import android.arch.lifecycle.MutableLiveData
import com.framgia.domain.usecase.GetGenresMovieUseCase
import com.framgia.newyorktime.base.viewmodel.BaseViewModel
import com.framgia.newyorktime.model.GenreItem
import com.framgia.newyorktime.model.GenreItemMapper
import com.framgia.newyorktime.rx.SchedulerProvider
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created: 02/08/2018
 * By: Sang
 * Description:
 */
class NowPlayingMoviesViewModel @Inject constructor(
    private val getGenresMovieUseCase: GetGenresMovieUseCase,
    private val schedulerProvider: SchedulerProvider,
    private val genreItemMapper: GenreItemMapper
) : BaseViewModel(getGenresMovieUseCase) {

    val genres = MutableLiveData<List<GenreItem>>()
    val loadGenreError = MutableLiveData<String>()

    fun getGenre() {
        val disposable = getGenresMovieUseCase.createObservable(GetGenresMovieUseCase.Params())
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .map { it.map { genre -> genreItemMapper.mapToPresentation(genre) } }
            .subscribeBy(
                onSuccess = {
                    genres.value = it
                },
                onError = {
                    loadGenreError.value = it.message
                }
            )
        compositeDisposable.add(disposable)
    }
}