package com.framgia.newyorktime.ui.main.nowplayingmovies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentNowPlayingMoviesBinding
import com.framgia.newyorktime.ui.widget.EndlessRecyclerScrollListener

/**
 * Created: 02/08/2018
 * By: Sang
 * Description:
 */
class NowPlayingMoviesFragment :
    BaseFragment<FragmentNowPlayingMoviesBinding, NowPlayingMoviesViewModel>() {

    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter
    private lateinit var endlessRecyclerScrollListener: EndlessRecyclerScrollListener

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: NowPlayingMoviesViewModel
        get() = ViewModelProviders.of(this, viewModelFactory)
            .get(NowPlayingMoviesViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_now_playing_movies

    override fun initComponent(savedInstanceState: Bundle?) {
        setupMovieList()
        setupViewModel()
    }

    private fun setupMovieList() {
        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter()
        endlessRecyclerScrollListener = EndlessRecyclerScrollListener { viewModel.loadMoreMovies() }
        viewDataBinding.recyclerMovie.apply {
            adapter = nowPlayingMoviesAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            addOnScrollListener(endlessRecyclerScrollListener)
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            isLoadMore.observe(this@NowPlayingMoviesFragment, Observer {
                it?.let { endlessRecyclerScrollListener.isLoadMore = it }
            })
        }
    }

    companion object {

        fun newInstance() = NowPlayingMoviesFragment()
    }
}
