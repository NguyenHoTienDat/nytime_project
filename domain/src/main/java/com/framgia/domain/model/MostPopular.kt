package com.framgia.domain.model

import com.framgia.domain.base.Model

data class MostPopular(
        var id: String?,
        var url: String?,
        var byline: String?,
        var title: String?,
        var abstract: String?,
        var published_date: String?,
        var views: Int?,
        var medias: List<Media>? = mutableListOf()) : Model()
