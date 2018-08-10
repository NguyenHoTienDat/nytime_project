package com.framgia.newyorktime.ui.search

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.framgia.domain.usecase.GetSearchMoviesUseCase
import com.framgia.newyorktime.base.viewmodel.BaseViewModel
import com.framgia.newyorktime.model.MovieItem
import com.framgia.newyorktime.model.MovieItemMapper
import com.framgia.newyorktime.rx.SchedulerProvider
import com.framgia.newyorktime.util.custom.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created: 08/08/2018
 * By: Sang
 * Description:
 */
const val DENOUNCED_TIME = 300L

class SearchViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase,
    val schedulerProvider: SchedulerProvider,
    private val movieItemMapper: MovieItemMapper
) : BaseViewModel(getSearchMoviesUseCase) {

    var currentQuery: String = ""
    private var currentPage: Int = 0
    private var totalPage: Int = -1
    private var tempMovies = mutableListOf<MovieItem>()

    val isLoadMore = MutableLiveData<Boolean>()
    val isLoadData = MutableLiveData<Boolean>()
    val movies = MutableLiveData<List<MovieItem>>()
    val openMovieDetailEvent = SingleLiveEvent<MovieItem>()

    fun searchMovies(searchObservable: Observable<String>) {
        val disposable = searchObservable
            .debounce(DENOUNCED_TIME, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .filter { !it.isEmpty() }
            .flatMap {
                currentPage = 1
                tempMovies.clear()
                isLoadData.postValue(true)
                getSearchObservable(it)
            }
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribeBy(
                onNext = {
                    isLoadData.value = false
                    tempMovies.addAll(it)
                    movies.value = tempMovies
                    currentPage++
                },
                onError = {
                    isLoadData.value = false
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun getSearchObservable(query: String): Observable<List<MovieItem>> =
        getSearchMoviesUseCase.createObservable(GetSearchMoviesUseCase.Params(query, currentPage))
            .map {
                currentQuery = query
                totalPage = it.pageCount
                it.movies.map { movie -> movieItemMapper.mapToPresentation(movie) }
            }.subscribeOn(schedulerProvider.io())

    fun loadMoreSearchMovies() {
        // Khi có list cũ (A) và list mới (B) + A.size > B.size
        // load more sẽ biến isLoadMore của EndlessScroll = true nhưng không set lại bằng false
        // do đó phải set lại isLoadMore.value = false để đảm bảo
        // sau khi loadMore ở bất cứ list nào thì isLoadMore của EndlessScroll luôn bằng false
        isLoadMore.value = false
        if (currentPage <= totalPage) {
            isLoadMore.value = true
            val disposable = getSearchMoviesUseCase
                .createObservable(GetSearchMoviesUseCase.Params(currentQuery, currentPage))
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .map { it.movies.map { movie -> movieItemMapper.mapToPresentation(movie) } }
                .subscribeBy(
                    onNext = {
                        isLoadMore.value = false
                        tempMovies.addAll(it)
                        movies.value = tempMovies
                        currentPage++
                    },
                    onError = {
                        isLoadMore.value = false
                    }
                )
            compositeDisposable.add(disposable)
        }
    }
}
