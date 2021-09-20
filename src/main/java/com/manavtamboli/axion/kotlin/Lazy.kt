package com.manavtamboli.axion.kotlin

inline fun <T> Lazy<T>.applyLazily(crossinline block : T.() -> Unit) : Lazy<T> = lazy { value.apply(block) }