package com.framgia.newyorktime.ui.topstories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.framgia.newyorktime.BR
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.fragment.BaseFragment
import com.framgia.newyorktime.base.recyclerview.BaseUserActionsListener
import com.framgia.newyorktime.databinding.FragmentTopStoriesBinding
import com.framgia.newyorktime.model.nytime.StoryGenreItem
import com.framgia.newyorktime.util.SharedPreUtils
import kotlinx.android.synthetic.main.fragment_top_stories.*

class TopStoriesFragment : BaseFragment<FragmentTopStoriesBinding, TopStoriesViewModel>()
        , BaseUserActionsListener<StoryGenreItem> {

    companion object {
        fun newInstance() = TopStoriesFragment()
    }

    private lateinit var genreAdapter: GenreAdapter
    private lateinit var storyAdapter: TopStoryAdapter

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

        storyAdapter = TopStoryAdapter {}.apply { recycler_story.adapter = this }
        genreAdapter = GenreAdapter(this)
        genreAdapter.run {
            recycler_genre.apply {
                adapter = this@run
                this@TopStoriesFragment.activity?.let {
                    scrollToPosition(SharedPreUtils.getStoryTypePos(it))
                }
            }

            submitList(viewModel.curGenres.apply {
                this@TopStoriesFragment.activity?.let {
                    this[SharedPreUtils.getStoryTypePos(it)].isSelected = true
                }
            })
        }

        swipe_stories.setOnRefreshListener {
            //update list data
            this@TopStoriesFragment.activity?.let {
                viewModel.getTopStories(SharedPreUtils.getStoryType(it), true)
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.stories.observe(this, Observer {
            it?.let { it1 ->
                storyAdapter.submitList(it1)
                recycler_story.scrollToPosition(0)
                viewModel.makeLoadingState(false)
                if (swipe_stories.isRefreshing) {
                    swipe_stories.isRefreshing = false

                }
            }
        })
    }

    override fun onItemViewClick(v: View, item: StoryGenreItem, position: Int) {
        genreAdapter.run {
            this@TopStoriesFragment.activity?.let {
                SharedPreUtils.saveStoryType(it, item.name)
                SharedPreUtils.saveStoryTypePos(it, position)
            }

            //update choose item
            submitList(viewModel.generateGenres().apply {
                map { storyGenreItem: StoryGenreItem -> storyGenreItem.isSelected = false }
                this[position].isSelected = true
            })
            recycler_genre.scrollToPosition(position)
        }

        //update list data
        viewModel.getTopStories(item.name)
    }
}
