package com.manavtamboli.axion.binding.adapters

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("viewSelected")
fun setViewSelected(view: View, viewSelected : Boolean){
    view.isSelected = viewSelected
}

@BindingAdapter("viewEnabled")
fun setViewEnabled(view: View, viewEnabled : Boolean){
    view.isEnabled = viewEnabled
}

@BindingAdapter("viewVisible")
fun setViewVisible(view: View, viewVisible : Boolean){
    view.isVisible = viewVisible
}