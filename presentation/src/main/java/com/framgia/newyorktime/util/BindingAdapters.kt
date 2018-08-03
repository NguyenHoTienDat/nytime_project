package com.framgia.newyorktime.util

import android.databinding.BindingAdapter
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.framgia.newyorktime.R

@BindingAdapter("itemDivider")
fun setItemDivider(view: RecyclerView, state: Boolean) {
    when (state) {
        true -> {
            view.addItemDecoration(DividerItemDecoration(view.context, LinearLayoutManager.VERTICAL))
        }
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url ?: "")
            .apply(RequestOptions().placeholder(R.drawable.ic_no_image))
            .into(view)
}

@BindingAdapter("setSelected")
fun setViewSelected(view: ImageView, state: Boolean) {
    view.isSelected = state
}
