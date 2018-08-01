package com.framgia.newyorktime.util

import android.content.Context
import android.os.IBinder
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.framgia.newyorktime.R
import dagger.android.support.AndroidSupportInjection

/**
 * Created: 31/07/2018
 * By: Sang
 * Description:
 */
fun Fragment.dismissKeyboard(window: IBinder) {
    activity?.apply {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(window, 0)
    }
}

fun Fragment.hideKeyboard() {
    activity?.apply {
        val view = currentFocus
        view?.let { this@hideKeyboard.dismissKeyboard(view.windowToken) }
    }
}

fun Fragment.showKeyBoardFromEditText(editText: EditText) {
    activity?.apply {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Fragment.findFragmentByTag(tag: String): Fragment? =
    activity?.run { this.supportFragmentManager.findFragmentByTag(tag) }

fun Fragment.replaceFragment(tag: String, isAddToBackStack: Boolean = false, transit: Int = -1) {
    activity?.apply {
        val transaction =
            supportFragmentManager.beginTransaction().replace(R.id.container, this@replaceFragment)
        if (isAddToBackStack) transaction.addToBackStack(tag)
        if (transit != -1) transaction.setTransition(transit)
        transaction.commit()
    }
}

fun Fragment.performDependenceInjection() {
    AndroidSupportInjection.inject(this)
}