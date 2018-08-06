package com.framgia.newyorktime.ui.main.nowplayingmovies

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentNowPlayingMoviesBinding

/**
 * Created: 02/08/2018
 * By: Sang
 * Description:
 */
class NowPlayingMoviesFragment :
    BaseFragment<FragmentNowPlayingMoviesBinding, NowPlayingMoviesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: NowPlayingMoviesViewModel
        get() = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(NowPlayingMoviesViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_now_playing_movies

    override fun retrieveOrRestoreState(savedInstanceState: Bundle?) {

    }

    companion object {

        fun newInstance() = NowPlayingMoviesFragment()
    }
}
