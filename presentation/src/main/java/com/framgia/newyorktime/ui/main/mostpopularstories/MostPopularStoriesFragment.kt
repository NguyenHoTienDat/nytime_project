package com.framgia.newyorktime.ui.main.mostpopularstories

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentMostPopularStoriesBinding

/**
 * Created: 03/08/2018
 * By: Sang
 * Description:
 */
class MostPopularStoriesFragment :
    BaseFragment<FragmentMostPopularStoriesBinding, MostPopularStoriesViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: MostPopularStoriesViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(MostPopularStoriesViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_most_popular_stories

    override fun retrieveOrRestoreState(savedInstanceState: Bundle?) {

    }

    companion object {

        fun newInstance(): MostPopularStoriesFragment = MostPopularStoriesFragment()
    }
}