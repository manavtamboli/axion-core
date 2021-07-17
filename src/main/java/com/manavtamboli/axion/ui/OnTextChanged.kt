package com.manavtamboli.axion.ui

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter

fun interface OnTextChanged {
    fun onTextChanged(text: String)

    companion object {

        @JvmStatic
        @BindingAdapter("onTextChanged")
        fun onTextChanged(editText: EditText, onTextChanged: OnTextChanged){
            editText.doAfterTextChanged { onTextChanged.onTextChanged(it.toString()) }
        }

    }
}