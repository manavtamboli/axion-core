package com.manavtamboli.axion.ui

import androidx.activity.ComponentActivity
import androidx.annotation.StyleRes
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.manavtamboli.axion.lifecycle.LifecycleLazy.Companion.lifecycleLazy
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

fun DatePicker(
    title : String? = null,
    selection : Long? = null,
    inputMode : Int? = null,
    @StyleRes theme : Int? = null,
    constraints : CalendarConstraints? = null,
    block : MaterialDatePicker<Long>.() -> Unit = {  }
) : MaterialDatePicker<Long> = MaterialDatePicker.Builder.datePicker().apply {
        setCalendarConstraints(constraints)
        setSelection(selection)
        title?.let(this::setTitleText)
        inputMode?.let(this::setInputMode)
        theme?.let(this::setTheme)
    }.build().apply(block)

fun Fragment.DatePickers(
    title : String? = null,
    selection : Long? = null,
    inputMode : Int? = null,
    @StyleRes theme : Int? = null,
    constraints : CalendarConstraints? = null,
    block : MaterialDatePicker<Long>.() -> Unit = {  }
) : Lazy<MaterialDatePicker<Long>> = lifecycleLazy { com.manavtamboli.axion.ui.DatePicker(title, selection, inputMode, theme, constraints, block) }

fun ComponentActivity.DatePickers(
    title : String? = null,
    selection : Long? = null,
    inputMode : Int? = null,
    @StyleRes theme : Int? = null,
    constraints : CalendarConstraints? = null,
    block : MaterialDatePicker<Long>.() -> Unit = {  }
) : Lazy<MaterialDatePicker<Long>> = lifecycleLazy { com.manavtamboli.axion.ui.DatePicker(title, selection, inputMode, theme, constraints, block) }

fun LifecycleOwner.DateRangePicker(
    title : String? = null,
    selection : Pair<Long, Long>? = null,
    inputMode : Int? = null,
    @StyleRes theme : Int? = null,
    constraints : CalendarConstraints? = null,
    block : MaterialDatePicker<Pair<Long, Long>>.() -> Unit = {  }
) = lifecycleLazy {
    MaterialDatePicker.Builder.dateRangePicker().apply {
        setCalendarConstraints(constraints)
        setSelection(selection)
        title?.let(this::setTitleText)
        inputMode?.let(this::setInputMode)
        theme?.let(this::setTheme)
    }.build().apply(block)
}

object CalendarConstraints {

    inline fun constraints(crossinline block : CalendarConstraints.Builder.() -> Unit) = CalendarConstraints.Builder().apply(block).build()

    fun from(point: Long) = constraints { setValidator(DateValidatorPointForward.from(point)) }
    fun before(point: Long) = constraints { setValidator(DateValidatorPointBackward.before(point)) }

    fun fromToday() = constraints { setValidator(DateValidatorPointForward.now()) }
    fun uptoToday() = constraints { setValidator(DateValidatorPointBackward.now()) }

    fun between(from: Long, to: Long) = constraints { setValidator(DateValidatorPointBetween(from, to)) }
}

@Parcelize
class DateValidatorPointBetween(val from : Long, val to : Long) : CalendarConstraints.DateValidator {
    override fun isValid(date: Long) = date in from..to
}