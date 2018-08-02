package com.framgia.newyorktime.util

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.framgia.domain.model.Movie

/**
 * Created: 02/08/2018
 * By: Sang
 * Description:
 */
object BindingAdapter{

    @BindingAdapter("movies")
    @JvmStatic
    fun RecyclerView.setMovieList(movies: List<Movie>){

    }
}
