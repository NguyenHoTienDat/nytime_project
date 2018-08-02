package com.framgia.newyorktime.model

import com.framgia.domain.model.SpokenLanguage
import com.framgia.newyorktime.base.model.ItemMapper
import com.framgia.newyorktime.base.model.ModelItem
import javax.inject.Inject

/**
 * Created: 02/08/2018
 * By: Sang
 * Description:
 */
class SpokenLanguageItem(val name: String) : ModelItem()

class SpokenLanguageItemMapper @Inject constructor() :
    ItemMapper<SpokenLanguage, SpokenLanguageItem> {

    override fun mapToPresentation(model: SpokenLanguage): SpokenLanguageItem =
        SpokenLanguageItem(name = model.name)

    override fun mapToDomain(modelItem: SpokenLanguageItem): SpokenLanguage =
        SpokenLanguage(name = modelItem.name)
}
