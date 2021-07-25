package com.manavtamboli.axion.kotlin

fun <T> List<T>.replace(newItem : T, which : (T) -> Boolean) = map { if (which(it)) newItem else it }

fun <T> List<T>.addOrReplace(newItem: T, which: (T) -> Boolean) : List<T> {
    val index = indexOfFirst(which)
    return if (index == -1) toMutableList().apply { add(newItem) }
    else replace(newItem, which)
}