package com.manavtamboli.axion.extensions

import android.os.Bundle
import android.os.Parcelable
import kotlin.reflect.KProperty

//  Not Tested
inline operator fun <reified T> Bundle.getValue(thisRef : Any?, prop : KProperty<*>) : T {
    val key = prop.name
    return when (T::class){
        Int::class -> getInt(key) as T
        String::class -> getString(key) as T
        else -> getParcelable<Parcelable>(key) as T
    }
}