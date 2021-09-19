package com.manavtamboli.axion.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

@Suppress("Unchecked_Cast")
internal fun <B : ViewBinding> Class<B>.inflate(inflater: LayoutInflater, viewGroup: ViewGroup?, attachToParent : Boolean) : B {
    return getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        .invoke(null, inflater, viewGroup, attachToParent) as B
}