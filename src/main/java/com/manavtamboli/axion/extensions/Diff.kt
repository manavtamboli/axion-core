package com.manavtamboli.axion.extensions

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KProperty1

fun <C> SimpleDiff() = object : DiffUtil.ItemCallback<C>(){
    override fun areItemsTheSame(oldItem: C, newItem: C) = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: C, newItem: C) = oldItem == newItem
}

fun <C> UniqueDiff(areItemsTheSame : (C, C) -> Boolean) = object : DiffUtil.ItemCallback<C>(){
    override fun areItemsTheSame(oldItem: C, newItem: C) = areItemsTheSame(oldItem, newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: C, newItem: C) = oldItem == newItem
}

fun <C> UniqueDiff(property : KProperty1<C, *>) = UniqueDiff<C> { oldItem, newItem -> property.get(oldItem) == property.get(newItem) }