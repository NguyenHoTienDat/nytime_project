package com.framgia.domain.usecase

import com.framgia.domain.base.UseCase
import com.framgia.domain.model.MostPopular
import com.framgia.domain.repository.PopularRepository
import io.reactivex.Single
import javax.inject.Inject

class GetEmailPopularUsecase @Inject constructor(private val popularRepo: PopularRepository)
    : UseCase<GetEmailPopularUsecase.Params, Single<List<MostPopular>>>() {

    override fun createObservable(params: Params): Single<List<MostPopular>> {
        return popularRepo.getMostEmailPopular()
    }

    override fun onCleared() {

    }

    class Params
}
