package com.manavtamboli.axion.ui

import androidx.annotation.ColorRes
import androidx.lifecycle.LifecycleOwner
import com.manavtamboli.axion.core.Arc
import com.manavtamboli.axion.core.Arc.Companion.context
import com.manavtamboli.axion.extensions.window
import com.manavtamboli.axion.lifecycle.doOnStart

fun LifecycleOwner.navBarColor(@ColorRes color : Int){
    doOnStart {
        window.navigationBarColor = Arc.require().context.getColor(color)
    }
}

fun LifecycleOwner.statusBarColor(@ColorRes color: Int) {
    doOnStart {
        window.statusBarColor = Arc.require().context.getColor(color)
    }
}

fun LifecycleOwner.systemBarsColor(@ColorRes color: Int){
    doOnStart {
        val colorInt = Arc.require().context.getColor(color)
        window.apply {
            navigationBarColor = colorInt
            statusBarColor = colorInt
        }
    }
}