package com.manavtamboli.axion.extensions

fun String.nullIfBlank() : String? = if (isNullOrBlank()) null else this

fun String.removeWhites() : String = replace("\\s".toRegex(), "")