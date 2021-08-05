package com.manavtamboli.axion.binding.adapters

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter

@BindingAdapter("adapter")
fun setAdapter(autoCompleteTextView: AutoCompleteTextView, adapter : ArrayAdapter<*>){
    autoCompleteTextView.setAdapter(adapter)
}