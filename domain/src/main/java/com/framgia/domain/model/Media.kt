package com.framgia.domain.model

import com.framgia.domain.base.Model

data class Media(
        val type: String = "",
        val caption: String = "",
        val media_metadata: List<MediaMetadata> = mutableListOf()) : Model()
