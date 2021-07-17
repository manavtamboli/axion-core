package com.manavtamboli.axion.core

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class ArcInitializer : Initializer<Arc> {

    override fun create(context : Context) : Arc {
        require(context is Application) { "Context must be Application Context" }
        return Arc.getInstance(context)
    }

    override fun dependencies() : MutableList<Class<out Initializer<*>>> = mutableListOf()
}