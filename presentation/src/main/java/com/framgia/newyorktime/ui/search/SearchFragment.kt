package com.framgia.newyorktime.ui.search

import android.arch.lifecycle.Observer
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ImageView
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentSearchBinding
import com.framgia.newyorktime.model.MovieItem
import com.framgia.newyorktime.ui.moviedetail.MovieDetailFragment
import com.framgia.newyorktime.ui.widget.EndlessRecyclerScrollListener
import com.framgia.newyorktime.util.getViewModelFragmentBound
import com.framgia.newyorktime.util.replaceFragment
import com.framgia.newyorktime.util.searchByQuery
import com.framgia.newyorktime.util.setupTheme

/**
 * Created: 08/08/2018
 * By: Sang
 * Description:
 */
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(),
    SearchItemNavigator,
    SearchNavigator {

    private lateinit var endlessRecyclerScrollListener: EndlessRecyclerScrollListener

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: SearchViewModel
        get() = getViewModelFragmentBound(SearchViewModel::class.java, viewModelFactory)

    override val layoutId: Int
        get() = R.layout.fragment_search

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSearchTheme()
        super.onCreate(savedInstanceState)
    }

    private fun setupSearchTheme() {
        setupTheme {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
            }
        }
    }

    override fun initComponent(savedInstanceState: Bundle?) {
        customSearchWidget()
        setupMovieList()
        setupSearch()
        setupViewModel()
    }

    private fun customSearchWidget() {
        viewDataBinding.search.apply {
            val searchButton = findViewById<ImageView>(R.id.search_mag_icon)
            searchButton.setImageResource(R.drawable.ic_arrow_back)
            searchButton.setOnClickListener { _ -> backToMain() }

            val searchText = findViewById<SearchView.SearchAutoComplete>(R.id.search_src_text)
            searchText.setTextColor(ContextCompat.getColor(context, android.R.color.white))

            val closeSearchButton = findViewById<ImageView>(R.id.search_close_btn)
            closeSearchButton.setColorFilter(ContextCompat.getColor(context, android.R.color.white))
        }
    }

    private fun setupMovieList() {
        endlessRecyclerScrollListener =
                EndlessRecyclerScrollListener { viewModel.loadMoreSearchMovies() }
        viewDataBinding.recyclerData.apply {
            adapter = SearchAdapter(object : SearchUserActionsListener {

                override fun onItemViewClick(v: View, item: MovieItem, position: Int) {
                    viewModel.openMovieDetailEvent.value = item
                }
            })
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(endlessRecyclerScrollListener)
        }
    }

    private fun setupSearch() {
        val searchObservable = viewDataBinding.search.searchByQuery(viewModel.currentQuery)
        viewModel.searchMovies(searchObservable)
    }

    private fun setupViewModel() {
        viewModel.apply {
            isLoadMore.observe(this@SearchFragment, Observer {
                it?.let { endlessRecyclerScrollListener.isLoadMore = it }
            })
            openMovieDetailEvent.observe(this@SearchFragment, Observer {
                it?.let { openMovieDetail(it) }
            })
        }
    }

    override fun openMovieDetail(movieItem: MovieItem) {
        val movieDetailFragment = MovieDetailFragment.newInstance(movieItem)
        replaceFragment(movieDetailFragment, MovieDetailFragment.MOVIE_DETAIL_TAG, true)
    }

    override fun backToMain() {
        activity?.onBackPressed()
    }

    companion object {

        const val SEARCH_TAG = "search"

        fun newInstance() = SearchFragment()
    }
}
