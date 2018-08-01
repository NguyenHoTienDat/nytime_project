package com.framgia.data.local.pref

import android.content.Context
import javax.inject.Inject

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
class AppPref @Inject constructor(private val context: Context) : PrefHelper {

    override fun isFirstRun(): Boolean {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val firstRun = sharedPref.getBoolean(FIRST_RUN, true)
        if (firstRun) {
            sharedPref.edit().putBoolean(FIRST_RUN, firstRun).apply()
        }
        return firstRun
    }

    companion object {

        const val SHARED_PREF_NAME = "com.framgia.cleanarchitecturesample.sharedpref"
        const val FIRST_RUN = "com.framgia.cleanarchitecturesample.sharedpref.FIRST_RUN"
    }
}
