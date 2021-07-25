package com.manavtamboli.axion.binding.adapters

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout

@BindingAdapter("onTabSelected")
fun setTabSelectedListener(tabLayout: TabLayout, listener : TabLayout.OnTabSelectedListener){
    tabLayout.addOnTabSelectedListener(listener)
}