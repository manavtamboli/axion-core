package com.manavtamboli.axion.kotlin

import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty

operator fun <T> StateFlow<T>.getValue(thisRef : Any?, prop : KProperty<*>) = value