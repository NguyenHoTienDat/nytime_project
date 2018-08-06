package com.framgia.newyorktime.ui.main.topratemovies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentTopRateMoviesBinding
import com.framgia.newyorktime.ui.widget.EndlessRecyclerScrollListener

/**
 * Created: 03/08/2018
 * By: Sang
 * Description:
 */
class TopRateMoviesFragment : BaseFragment<FragmentTopRateMoviesBinding, TopRateMoviesViewModel>() {

    private lateinit var endlessRecyclerScrollListener: EndlessRecyclerScrollListener

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: TopRateMoviesViewModel
        get() = ViewModelProviders.of(this, viewModelFactory)
            .get(TopRateMoviesViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_top_rate_movies

    override fun initComponent(savedInstanceState: Bundle?) {
        setupMovieList()
        setupViewModel()
    }

    private fun setupMovieList() {
        endlessRecyclerScrollListener = EndlessRecyclerScrollListener { viewModel.loadMoreMovies() }
        viewDataBinding.recyclerMovie.apply {
            adapter = TopRateMoviesAdapter()
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            addOnScrollListener(endlessRecyclerScrollListener)
        }
    }

    private fun setupViewModel() {
        viewModel.isLoadMore.observe(this, Observer {
            it?.let { endlessRecyclerScrollListener.isLoadMore = it }
        })
    }

    companion object {

        fun newInstance() = TopRateMoviesFragment()
    }
}
