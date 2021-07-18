package com.manavtamboli.axion.extensions

import java.util.*

fun randomUUId() = UUID.randomUUID().toString()

fun rawUUId() = randomUUId().replace("-", "")