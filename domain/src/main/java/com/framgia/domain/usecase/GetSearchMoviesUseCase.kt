package com.framgia.domain.usecase

import com.framgia.domain.base.UseCase
import com.framgia.domain.model.Movie
import com.framgia.domain.model.MovieInfo
import com.framgia.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created: 02/08/2018
 * By: Sang
 * Description:
 */
class GetSearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<GetSearchMoviesUseCase.Params, Observable<MovieInfo>>() {

    override fun createObservable(params: Params): Observable<MovieInfo> =
        movieRepository.getSearchMovies(params.query, params.page)

    override fun onCleared() {
        // No-op
    }

    data class Params(val query: String, val page: Int)
}
