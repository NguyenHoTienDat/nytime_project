package com.framgia.data.repositoryimpl

import com.framgia.data.model.MostPopularMapperEntity
import com.framgia.data.remote.api.MostPopularApi
import com.framgia.domain.model.MostPopular
import com.framgia.domain.repository.PopularRepository
import io.reactivex.Single
import javax.inject.Inject

class PopularRepositoryImp @Inject constructor(
        private val popularApi: MostPopularApi,
        private val mapper: MostPopularMapperEntity
) : PopularRepository {

    override fun getMostViewPopular(): Single<List<MostPopular>> {
        return popularApi.getMostViewPopular().map {
            it.results.map { mapper.mapToDomain(it) }
        }
    }
}
