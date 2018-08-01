package com.framgia.data.model

import com.framgia.data.base.ModelEntity
import com.framgia.domain.model.MultiMedia
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class MultimediaEntity(
        @SerializedName("url")
        var url: String,
        @SerializedName("caption")
        var caption: String) : ModelEntity()

class MultimediaEntityMapper @Inject constructor() {

    fun mapListToDomain(entities: List<MultimediaEntity>) = mutableListOf<MultiMedia>().apply {
        entities.forEach { it ->
            this.add(MultiMedia(url = it.url, caption = it.caption))
        }
    }

    fun mapListToEntity(models: List<MultiMedia>) = mutableListOf<MultimediaEntity>().apply {
        models.forEach { it ->
            this.add(MultimediaEntity(url = it.url, caption = it.caption))
        }
    }
}
