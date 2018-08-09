package com.framgia.newyorktime.ui.search

import com.framgia.newyorktime.base.fragment.BaseItemNavigator
import com.framgia.newyorktime.model.MovieItem

/**
 * Created: 08/08/2018
 * By: Sang
 * Description:
 */
interface SearchItemNavigator : BaseItemNavigator {

    fun openMovieDetail(movieItem: MovieItem)
}
