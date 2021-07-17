package com.manavtamboli.axion.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider
import com.manavtamboli.axion.core.Delicate

fun interface OnSliderValueChanged {

    fun onValueChange(value : Float, fromUser : Boolean)

    companion object {

        @JvmStatic
        @Delicate("Must be set only once.")
        @BindingAdapter("onSliderValueChanged")
        fun addOnValueChanged(slider: Slider, onSliderValueChanged : OnSliderValueChanged? = null){
            onSliderValueChanged?.let {
                slider.addOnChangeListener { _, value, fromUser -> it.onValueChange(value, fromUser) }
            }
        }
    }
}