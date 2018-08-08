package com.framgia.newyorktime.ui.main.mostpopularstories

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.framgia.newyorktime.R
import com.framgia.newyorktime.base.recyclerview.BaseRecyclerAdapter
import com.framgia.newyorktime.base.recyclerview.BaseUserActionsListener
import com.framgia.newyorktime.databinding.ItemPopularBinding
import com.framgia.newyorktime.model.nytime.PopularItem
import com.framgia.newyorktime.util.DateTimeUtil

class PopularAdapter(private val itemListener: OnPopularItemClickListener)
    : BaseRecyclerAdapter<PopularItem, ItemPopularBinding>(
        diffCallback = object : DiffUtil.ItemCallback<PopularItem>() {
            override fun areItemsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PopularItem, newItem: PopularItem): Boolean {
                return oldItem == newItem
            }

        }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int?): ItemPopularBinding {
        return DataBindingUtil.inflate<ItemPopularBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_popular, parent,
                false).apply {
            root.setOnClickListener {
                popularItem?.let { popular -> itemListener.onItemViewClick(root, popular, 11) }
            }
        }
    }

    override fun bind(binding: ItemPopularBinding, item: PopularItem, itemPosition: Int) {
        binding.apply {
            popularItem = item
            position = itemPosition
            listener = itemListener
            dateUtil = DateTimeUtil
            root.setOnClickListener {
                popularItem?.let { popular -> itemListener.onItemViewClick(root, popular, 11) }
            }
        }
    }

    interface OnPopularItemClickListener : BaseUserActionsListener<PopularItem> {
        fun onSaveClick(item: PopularItem)
        fun onShareClick(item: PopularItem)
    }
}
