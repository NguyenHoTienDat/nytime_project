package com.framgia.newyorktime

import android.os.Bundle
import android.util.Log
import com.framgia.data.repositoryimpl.PopularRepositoryImp
import com.framgia.newyorktime.base.BaseActivity
import com.framgia.newyorktime.ui.main.MainFragment
import dagger.android.AndroidInjection
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var popular : PopularRepositoryImp
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        popular.getMostViewPopular()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                {
                    Log.d("xxxx", it[0].toString())
                },
                {
                    Log.d("xxxx", it.toString())
                }
        )
    }
}
