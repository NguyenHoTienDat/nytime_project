package com.framgia.newyorktime.ui.offlinemovie

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentOfflineMovieBinding
import com.framgia.newyorktime.util.getViewModelFragmentBound
import com.framgia.newyorktime.util.setupTheme

/**
 * Created: 09/08/2018
 * By: Sang
 * Description:
 */
class OfflineMovieFragment : BaseFragment<FragmentOfflineMovieBinding, OfflineMovieViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: OfflineMovieViewModel
        get() = getViewModelFragmentBound(OfflineMovieViewModel::class.java, viewModelFactory)

    override val layoutId: Int
        get() = R.layout.fragment_offline_movie

    override fun onCreate(savedInstanceState: Bundle?) {
        setupMovieDetailTheme()
        super.onCreate(savedInstanceState)
    }

    private fun setupMovieDetailTheme() {
        setupTheme {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
            }
        }
    }

    override fun initComponent(savedInstanceState: Bundle?) {

    }

    companion object {

        const val OFFLINE_MOVIE_TAG = "offline_movie"

        fun newInstance() = OfflineMovieFragment()
    }
}
