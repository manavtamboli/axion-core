package com.manavtamboli.axion.extensions

import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KProperty1

@Suppress("FunctionName")
fun <C> UniqueDiff(property : KProperty1<C, *>) = object : DiffUtil.ItemCallback<C>() {

    override fun areItemsTheSame(oldItem: C, newItem: C) = property.get(oldItem) == property.get(newItem)

    @Suppress("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: C, newItem: C) = oldItem == newItem
}