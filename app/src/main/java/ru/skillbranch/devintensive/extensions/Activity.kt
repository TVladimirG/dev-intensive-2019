package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.skillbranch.devintensive.R

fun Activity.hideKeyboard() {
    //  v.requestFocus()
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    // hideSoftInputFromWindow(v.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val constraintLayout = findViewById<View>(R.id.rootview)
    val rec = Rect()
    constraintLayout.getWindowVisibleDisplayFrame(rec)
    //finding screen height
    val screenHeight = constraintLayout.rootView.height
    //finding keyboard height
    val keypadHeight = screenHeight - rec.bottom
    return keypadHeight > screenHeight * 0.15
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}