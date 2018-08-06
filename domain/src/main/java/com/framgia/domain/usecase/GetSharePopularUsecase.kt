package com.framgia.domain.usecase

import com.framgia.domain.base.UseCase
import com.framgia.domain.model.MostPopular
import com.framgia.domain.repository.PopularRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSharePopularUsecase @Inject constructor(private val popularRepo: PopularRepository)
    : UseCase<GetSharePopularUsecase.Params, Single<List<MostPopular>>>() {

    override fun createObservable(params: Params): Single<List<MostPopular>> {
        return popularRepo.getMostSharePopular()
    }

    override fun onCleared() {

    }

    class Params
}
