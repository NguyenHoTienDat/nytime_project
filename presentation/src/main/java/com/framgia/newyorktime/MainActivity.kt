package com.framgia.newyorktime

import android.os.Bundle
import com.framgia.newyorktime.base.BaseActivity
import com.framgia.newyorktime.ui.topstories.TopStoriesFragment
import dagger.android.AndroidInjection

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TopStoriesFragment.newInstance())
                    .commitNow()

        }
    }
}
