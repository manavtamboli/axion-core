package com.manavtamboli.axion.extensions

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

object DateTimeFormatters {

    fun dayOfMonthSuffixes() : Map<Long, String> = mutableMapOf<Long, String>().apply {
        repeat(31){
            val day = it + 1
            val suffix = when (day) {
                1, 21, 31 -> "st"
                2, 22 -> "nd"
                3, 23 -> "rd"
                else -> "th"
            }
            put(day.toLong(), "$day$suffix")
        }
    }

    val ThreeDigitMonth : DateTimeFormatter get() = ofPattern("MMM")

    val dd_MMM : DateTimeFormatter get() = ofPattern("dd MMM")

    val dd_MMM_SUFFIXED : DateTimeFormatter get() = DateTimeFormatterBuilder()
        .appendText(ChronoField.DAY_OF_MONTH, dayOfMonthSuffixes())
        .append(ofPattern(" MMM"))
        .toFormatter()
}