package com.framgia.data.model.moviedb

import com.framgia.data.base.EntityMapper
import com.framgia.data.base.ModelEntity
import com.framgia.domain.model.moviedb.ProductionCountry

/**
 * Created: 01/08/2018
 * By: Sang
 * Description:
 */
class ProductionCountryEntity(
    val iso31661: String = "",
    val name: String = ""
) : ModelEntity()

class ProductionCountryEntityMapper : EntityMapper<ProductionCountry, ProductionCountryEntity> {

    override fun mapToDomain(entity: ProductionCountryEntity): ProductionCountry =
        ProductionCountry(entity.iso31661, entity.name)

    override fun mapToEntity(model: ProductionCountry): ProductionCountryEntity =
        ProductionCountryEntity(model.iso31661, model.name)
}
