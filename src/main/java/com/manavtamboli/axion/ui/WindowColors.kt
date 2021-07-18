package com.manavtamboli.axion.ui

import androidx.annotation.ColorRes
import androidx.lifecycle.LifecycleOwner
import com.manavtamboli.axion.core.Arc
import com.manavtamboli.axion.core.Arc.Companion.context
import com.manavtamboli.axion.extensions.window
import com.manavtamboli.axion.lifecycle.doOnStart

fun LifecycleOwner.navColor(@ColorRes color : Int){
    doOnStart {
        window.navigationBarColor = Arc.require().context.getColor(color)
    }
}

fun LifecycleOwner.statusColor(@ColorRes color: Int) {
    doOnStart {
        window.statusBarColor = Arc.require().context.getColor(color)
    }
}

fun LifecycleOwner.systemColor(@ColorRes color: Int){
    doOnStart {
        val colorInt = Arc.require().context.getColor(color)
        window.apply {
            navigationBarColor = colorInt
            statusBarColor = colorInt
        }
    }
}