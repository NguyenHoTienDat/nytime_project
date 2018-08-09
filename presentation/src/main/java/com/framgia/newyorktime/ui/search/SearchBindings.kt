package com.framgia.newyorktime.ui.search

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.framgia.newyorktime.model.MovieItem

/**
 * Created: 08/08/2018
 * By: Sang
 * Description:
 */
object SearchBindings {

    @BindingAdapter("searchMoviesItems")
    @JvmStatic
    fun RecyclerView.setItems(items: List<MovieItem>?) {
        when (adapter) {
            is SearchAdapter -> (adapter as SearchAdapter).setMovies(items)
        }
    }
}
