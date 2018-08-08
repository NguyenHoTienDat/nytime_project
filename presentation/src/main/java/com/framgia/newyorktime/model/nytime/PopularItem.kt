package com.framgia.newyorktime.model.nytime

import com.framgia.domain.model.MostPopular
import com.framgia.newyorktime.base.model.ItemMapper
import com.framgia.newyorktime.base.model.ModelItem
import javax.inject.Inject

class PopularItem(
        var id: String?,
        var url: String?,
        var byline: String?,
        var title: String?,
        var abstract: String?,
        var published_date: String?,
        var views: Int?,
        var mediaUrl: String?,
        var mediaCaption: String?) : ModelItem()

class PopularItemMapper @Inject constructor() : ItemMapper<MostPopular, PopularItem> {
    override fun mapToPresentation(model: MostPopular): PopularItem = PopularItem(
            id = model.id,
            url = model.url,
            byline = model.byline,
            title = model.title,
            abstract = model.abstract,
            published_date = model.published_date,
            views = model.views,
            mediaUrl = model.medias?.get(0)?.media_metadata?.get(0)?.url,
            mediaCaption = model.medias?.get(0)?.caption
    )

    override fun mapToDomain(modelItem: PopularItem): MostPopular = MostPopular(
            id = modelItem.id,
            url = modelItem.url,
            byline = modelItem.byline,
            title = modelItem.title,
            abstract = modelItem.abstract,
            published_date = modelItem.published_date,
            views = modelItem.views
    )
}
