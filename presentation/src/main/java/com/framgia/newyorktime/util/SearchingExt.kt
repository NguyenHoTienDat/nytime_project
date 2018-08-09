package com.framgia.newyorktime.util

import android.support.v7.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * Created: 08/08/2018
 * By: Sang
 * Description:
 */
fun SearchView.searchByQuery(oldQuery: String?): Observable<String> {
    val subject = PublishSubject.create<String>()
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String): Boolean {
            subject.onComplete()
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean =
            if (oldQuery == newText) false else {
                subject.onNext(newText)
                true
            }
    })
    return subject
}
