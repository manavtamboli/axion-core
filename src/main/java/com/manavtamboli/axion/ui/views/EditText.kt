package com.manavtamboli.axion.ui.views

import android.widget.EditText

val EditText.value get() = text.toString()

val EditText.integerValue get() = value.toInt()

fun EditText.validateNotBlank(error : String = "Required") : Boolean {
    return value.isBlank().also { if (!it) this.error = error }
}

inline fun EditText.validateNotBlank(action : () -> Unit){
    if (value.isBlank()) action()
}