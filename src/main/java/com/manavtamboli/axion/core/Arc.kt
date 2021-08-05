package com.manavtamboli.axion.core

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.util.DisplayMetrics
import androidx.annotation.StringRes
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

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

        inline operator fun <reified T> getValue(nothing: Nothing?, property: KProperty<*>): T {
            val cls = T::class.java
            require().run {
                return when {
                    cls.isAssignableFrom(Arc::class.java) -> this as T
                    cls.isAssignableFrom(Context::class.java) -> applicationContext as T
                    cls.isAssignableFrom(ContentResolver::class.java) -> contentResolver as T
                    cls.isAssignableFrom(DisplayMetrics::class.java) -> displayMetrics as T
                    else -> throw IllegalArgumentException()
                }
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