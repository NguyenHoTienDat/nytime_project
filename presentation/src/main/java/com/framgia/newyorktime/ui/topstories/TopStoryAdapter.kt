package com.framgia.newyorktime.ui.topstories

import android.support.v7.util.DiffUtil
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.recyclerview.BaseRecyclerViewAdapter
import com.framgia.newyorktime.base.recyclerview.BaseViewHolder
import com.framgia.newyorktime.databinding.ItemStoryBinding
import com.framgia.newyorktime.model.nytime.StoryItem
import com.framgia.newyorktime.util.DateTimeUtil

class TopStoryAdapter(private val callback: ((StoryItem) -> Unit)?)
    : BaseRecyclerViewAdapter<StoryItem, ItemStoryBinding, TopStoryAdapter.StoryHolder>(
        diffCallback = object : DiffUtil.ItemCallback<StoryItem>() {
            override fun areItemsTheSame(oldItem: StoryItem, newItem: StoryItem): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: StoryItem, newItem: StoryItem): Boolean {
                return oldItem == newItem
            }

        }
) {
    override fun onBindViewHolder(holder: StoryHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override val layoutInt: Int
        get() = R.layout.item_story

    override fun getViewHolder(viewDataBinding: ItemStoryBinding): StoryHolder = StoryHolder(viewDataBinding)


    inner class StoryHolder(binding: ItemStoryBinding) : BaseViewHolder<ItemStoryBinding>(binding) {
        fun bind(item: StoryItem) {
            binding.apply {
                story = item
                dateUtil = DateTimeUtil
                executePendingBindings()
            }

        }
    }
}
