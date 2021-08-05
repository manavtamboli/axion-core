package com.manavtamboli.axion.ui

import androidx.annotation.StyleRes
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.manavtamboli.axion.lifecycle.LifecycleLazy.Companion.lifecycleLazy

fun LifecycleOwner.DatePickers(
    title : String? = null,
    selection : Long = MaterialDatePicker.todayInUtcMilliseconds(),
    inputMode : Int? = null,
    @StyleRes theme : Int? = null,
    constraints : CalendarConstraints? = null,
    block : MaterialDatePicker<Long>.() -> Unit = {  }
) = lifecycleLazy {
    MaterialDatePicker.Builder.datePicker().apply {
        setCalendarConstraints(constraints)
        setSelection(selection)
        title?.let(this::setTitleText)
        inputMode?.let(this::setInputMode)
        theme?.let(this::setTheme)
    }.build().apply(block)
}

object Constraints {
    fun fromToday() = CalendarConstraints.Builder()
        .setValidator(DateValidatorPointForward.now())
        .build()
}