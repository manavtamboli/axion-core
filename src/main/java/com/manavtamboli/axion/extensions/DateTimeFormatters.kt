package com.manavtamboli.axion.extensions

import java.time.format.DateTimeFormatter

object DateTimeFormatters {
    val ThreeDigitMonth get() = DateTimeFormatter.ofPattern("MMM")
}