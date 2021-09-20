package com.manavtamboli.axion.ui

import androidx.activity.ComponentActivity
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.google.android.material.datepicker.MaterialDatePicker.Builder.dateRangePicker
import com.manavtamboli.axion.lifecycle.lifecycleLazy
import kotlinx.parcelize.Parcelize

inline fun Fragment.DatePicker(crossinline block: MaterialDatePicker.Builder<Long>.() -> Unit) =
    lifecycleLazy {
        datePicker()
            .apply(block)
            .build()
    }

inline fun ComponentActivity.DatePicker(crossinline block: MaterialDatePicker.Builder<Long>.() -> Unit) =
    lifecycleLazy {
        datePicker()
            .apply(block)
            .build()
    }

inline fun Fragment.DateRangePicker(crossinline block: MaterialDatePicker.Builder<Pair<Long, Long>>.() -> Unit) =
    lifecycleLazy {
        dateRangePicker()
            .apply(block)
            .build()
    }

inline fun ComponentActivity.DateRangePicker(crossinline block: MaterialDatePicker.Builder<Pair<Long, Long>>.() -> Unit) =
    lifecycleLazy {
        dateRangePicker()
            .apply(block)
            .build()
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