package com.manavtamboli.axion.binding.adapters

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider

@BindingAdapter("onValueChanged")
fun setOnValueChanged(slider: Slider, onValueChanged : Slider.OnChangeListener){
    slider.addOnChangeListener(onValueChanged)
}