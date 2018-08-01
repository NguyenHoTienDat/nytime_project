package com.framgia.domain.model.moviedb

import com.framgia.domain.model.Model

/**
 * Created: 01/08/2018
 * By: Sang
 * Description:
 */
data class Genre(
    val id: Int = 0,
    val name: String = ""
) : Model()
