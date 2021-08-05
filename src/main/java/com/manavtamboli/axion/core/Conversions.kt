package com.manavtamboli.axion.core

import android.util.TypedValue
import com.manavtamboli.axion.core.Arc.Companion.displayMetrics

// TODO : Replace with compose

val Float.dp get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Arc.require().displayMetrics)

val Int.dp get() = toFloat().dp

val Float.sp get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Arc.require().displayMetrics)

val Int.sp get() = toFloat().sp
