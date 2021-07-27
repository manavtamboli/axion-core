package com.manavtamboli.axion.extensions

import android.util.Log

fun Any.log(message : Any?) = Log.i(this::class.java.simpleName, message.toString())

fun Any.error(message : Any?) = Log.e(this::class.java.simpleName, message.toString())