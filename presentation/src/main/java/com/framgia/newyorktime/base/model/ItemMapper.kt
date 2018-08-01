package com.framgia.newyorktime.base.model

import com.framgia.domain.model.Model

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
interface ItemMapper<M : Model, MI : ModelItem> {

    fun mapToPresentation(model: M): ModelItem

    fun mapToDomain(modelItem: MI): Model
}
