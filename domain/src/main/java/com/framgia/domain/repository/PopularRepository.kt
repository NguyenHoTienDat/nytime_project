package com.framgia.domain.repository

import com.framgia.domain.model.MostPopular
import io.reactivex.Single

interface PopularRepository {
    fun getMostViewPopular() : Single<List<MostPopular>>
}
