package com.framgia.data.repositoryimpl

import com.framgia.data.model.MostPopularMapper
import com.framgia.data.remote.api.MostPopularApi
import com.framgia.domain.model.MostPopular
import com.framgia.domain.repository.PopularRepository
import io.reactivex.Single
import javax.inject.Inject

class PopularRepositoryImp @Inject constructor(
        private val popularApi: MostPopularApi,
        private val mapper: MostPopularMapper
) : PopularRepository {

    override fun getMostEmailPopular(): Single<List<MostPopular>> {
        return popularApi.getMostEmailPopular().map {
            it.results.map { mapper.mapToDomain(it) }
        }
    }

    override fun getMostViewPopular(): Single<List<MostPopular>> {
        return popularApi.getMostViewPopular().map {
            it.results.map { mapper.mapToDomain(it) }
        }
    }

    override fun getMostSharePopular(): Single<List<MostPopular>> {
        return popularApi.getMostSharePopular().map {
            it.results.map { mapper.mapToDomain(it) }
        }
    }
}
