package com.framgia.domain.repository

import com.framgia.domain.model.MostPopular
import io.reactivex.Single

interface PopularRepository {
    fun getMostEmailPopular() : Single<List<MostPopular>>

    fun getMostViewPopular() : Single<List<MostPopular>>

    fun getMostSharePopular() : Single<List<MostPopular>>
}
