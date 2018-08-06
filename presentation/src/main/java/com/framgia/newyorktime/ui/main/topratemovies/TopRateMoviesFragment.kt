package com.framgia.newyorktime.ui.main.topratemovies

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentTopRateMoviesBinding

/**
 * Created: 03/08/2018
 * By: Sang
 * Description:
 */
class TopRateMoviesFragment : BaseFragment<FragmentTopRateMoviesBinding, TopRateMoviesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: TopRateMoviesViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(TopRateMoviesViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_top_rate_movies

    override fun retrieveOrRestoreState(savedInstanceState: Bundle?) {

    }

    companion object {

        fun newInstance() = TopRateMoviesFragment()
    }
}
