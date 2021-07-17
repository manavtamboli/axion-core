package com.manavtamboli.axion.ui.views

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import com.manavtamboli.axion.core.Delicate

fun interface OnTabSelected {

    fun onTabSelected(tab : TabLayout.Tab?)

    companion object {

        fun onSelected(action: OnTabSelected) = object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) { action.onTabSelected(tab) }
            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            override fun onTabReselected(tab: TabLayout.Tab?) { }
        }

        @JvmStatic
        @Delicate("Must be set only Once")
        @BindingAdapter("onTabSelected")
        fun setOnTabSelected(tabLayout: TabLayout, onTabSelected : OnTabSelected? = null){
            onTabSelected ?: return
            tabLayout.addOnTabSelectedListener(onSelected(onTabSelected))
        }
    }
}