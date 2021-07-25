package com.manavtamboli.axion.core

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.StringRes

class Arc private constructor(val application: Application){

    companion object {

        @Volatile
        private var instance : Arc? = null

        fun getInstance(application: Application) : Arc {
            instance?.let { return it }
            synchronized(this){
                return Arc(application).also { instance = it }
            }
        }

        /**
         * The application context initialized at app startup.
         * Do not use this context to start activities or dialogs.
         * */
        val Arc.applicationContext : Context get() = application
        val Arc.contentResolver : ContentResolver get() = application.contentResolver
        val Arc.displayMetrics : DisplayMetrics get() = application.resources.displayMetrics

        fun Arc.str(@StringRes id : Int) = applicationContext.getString(id)

        fun require() = instance!!
    }
}