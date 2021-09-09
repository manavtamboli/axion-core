package com.manavtamboli.axion.extensions

import androidx.core.util.Pair

operator fun <F, S> Pair<F, S>.component1() : F = first

operator fun <F, S> Pair<F, S>.component2() : S = second