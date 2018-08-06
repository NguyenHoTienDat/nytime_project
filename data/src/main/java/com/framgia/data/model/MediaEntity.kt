package com.framgia.data.model

import com.framgia.data.base.EntityMapper
import com.framgia.data.base.ModelEntity
import com.framgia.domain.model.Media
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class MediaEntity(
        @SerializedName("type")
        val type: String = "",
        @SerializedName("caption")
        val caption: String = "",
        @SerializedName("media-metadata")
        val media_metadata: List<MediaMetadataEntity> = mutableListOf()
) : ModelEntity()

class MediaEntityMapper @Inject constructor(
        private val metaDataMapper: MediaMetadataEntityMapper
) : EntityMapper<Media, MediaEntity> {
    override fun mapToDomain(entity: MediaEntity): Media = Media(
            type = entity.type,
            caption = entity.caption,
            media_metadata = entity.media_metadata.map { metaDataMapper.mapToDomain(it) }
    )

    override fun mapToEntity(model: Media): MediaEntity = MediaEntity(
            type = model.type,
            caption = model.caption,
            media_metadata = model.media_metadata.map { metaDataMapper.mapToEntity(it) }
    )
}