package com.framgia.newyorktime.ui.main.topstories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.databinding.FragmentTopStoriesBinding
import kotlinx.android.synthetic.main.fragment_top_stories.*

class TopStoriesFragment: BaseFragment<FragmentTopStoriesBinding, TopStoriesViewModel>() {

    companion object {
        fun newInstance() = TopStoriesFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: TopStoriesViewModel
        get() = ViewModelProviders.of(this, viewModelFactory)
                .get(TopStoriesViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_top_stories

    override fun retrieveOrRestoreState(savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_story.adapter = TopStoryAdapter {}
        viewModel.stories.observe(this, Observer {
            when (recycler_story.adapter) {
                is TopStoryAdapter -> {
                    (recycler_story.adapter as TopStoryAdapter).submitList(it)
                }
            }
        })
    }
}
