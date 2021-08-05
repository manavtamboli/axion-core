package com.manavtamboli.axion.kotlin

import java.time.*

// region Epoch Millis
fun Long.toZonedDateTime() : ZonedDateTime {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault())
}

fun Long.toLocalDateTime() : LocalDateTime {
    return toZonedDateTime().toLocalDateTime()
}

fun Long.toLocalDate(): LocalDate {
    return toZonedDateTime().toLocalDate()
}
// endregion

// region LocalDate
fun LocalDate.toEpochMillis() : Long {
    return atStartOfDay().toEpochMillis()
}

fun LocalDate.toEpochMillis(hour : Int, minute : Int) : Long {
    return atTime(hour, minute).toEpochMillis()
}
// endregion

// region LocalDateTime
fun LocalDateTime.toEpochMillis() : Long {
    return atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
// endregion