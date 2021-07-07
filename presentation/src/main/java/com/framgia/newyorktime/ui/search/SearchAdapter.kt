package com.framgia.newyorktime.ui.search

import android.support.v7.util.DiffUtil
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.recyclerview.BaseRecyclerViewAdapter
import com.framgia.newyorktime.base.recyclerview.BaseViewHolder
import com.framgia.newyorktime.databinding.ItemFavoriteMovieBinding
import com.framgia.newyorktime.databinding.ItemMovieBinding
import com.framgia.newyorktime.model.MovieItem

/**
 * Created: 08/08/2018
 * By: Sang
 * Description:
 */
class SearchAdapter(private val userActionsListener: SearchUserActionsListener) :
    BaseRecyclerViewAdapter<MovieItem, ItemFavoriteMovieBinding, SearchAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<MovieItem>() {

            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.title == newItem.title
        }
    ) {

    override val layoutInt: Int
        get() = R.layout.item_favorite_movie

    override fun getViewHolder(viewDataBinding: ItemFavoriteMovieBinding): ViewHolder =
        ViewHolder(viewDataBinding)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            movie = getItem(position)
            listener = userActionsListener
            p = position
            executePendingBindings()
        }
    }

    fun setMovies(items: List<MovieItem>?) {
        items?.let {
            val newList = ArrayList<MovieItem>()
            it.forEach { movieItem -> newList.add(movieItem) }
            submitList(newList)
        }
    }

    class ViewHolder(binding: ItemFavoriteMovieBinding) : BaseViewHolder<ItemFavoriteMovieBinding>(binding)
}
