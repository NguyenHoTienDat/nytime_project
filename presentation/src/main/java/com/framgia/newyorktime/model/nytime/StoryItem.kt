package com.framgia.newyorktime.model.nytime

import com.framgia.domain.model.Story
import com.framgia.newyorktime.base.model.ItemMapper
import com.framgia.newyorktime.base.model.ModelItem
import com.framgia.newyorktime.util.convertNewsPublishTime
import javax.inject.Inject

data class StoryItem(val title: String,
                     val abstract: String,
                     val url: String,
                     val byline: String,
                     val imageUrl: String,
                     val publishDate: String) : ModelItem() {
    fun convertPublishDate() = publishDate.convertNewsPublishTime()
}

class StoryItemMapper @Inject constructor() : ItemMapper<Story, StoryItem> {

    override fun mapToDomain(item: StoryItem): Story = Story()

    override fun mapToPresentation(model: Story): StoryItem = StoryItem(
            title = model.title,
            abstract = model.abstract,
            url = model.url,
            byline = model.byline,
            imageUrl = if (model.multimedia.isNotEmpty()) model.multimedia[model.multimedia.size - 1].url else "",
            publishDate = model.publishedDate)
}
