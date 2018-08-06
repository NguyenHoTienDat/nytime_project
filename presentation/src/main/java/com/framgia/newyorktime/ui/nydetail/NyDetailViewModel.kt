package com.framgia.newyorktime.ui.nydetail

import android.app.Application
import com.framgia.newyorktime.base.viewmodel.BaseViewModel
import com.framgia.newyorktime.model.nytime.StoryItem
import com.framgia.newyorktime.rx.SchedulerProvider
import javax.inject.Inject

class NyDetailViewModel @Inject constructor(
        private val application: Application,
        private val schedulerProvider: SchedulerProvider) : BaseViewModel() {

        var storyItem: StoryItem? = null
}
