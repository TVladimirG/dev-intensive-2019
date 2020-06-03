package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {

  //  v.requestFocus()
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)

    // hideSoftInputFromWindow(v.windowToken, 0)

}

fun Activity.isKeyboardOpen() {

}

fun Activity.isKeyboardClosed() {

}