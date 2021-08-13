package com.manavtamboli.axion.kotlin

import java.time.LocalDate

data class DateRange(override val start: LocalDate, override val endInclusive: LocalDate) : ClosedRange<LocalDate> {

    interface Merger {
        val ranges : List<DateRange>
        fun addRange(range : DateRange) : List<DateRange>
    }
}

operator fun LocalDate.rangeTo(other : LocalDate) = DateRange(this, other)

private class MergerImpl : DateRange.Merger {

    private var _ranges = listOf<DateRange>()
    override val ranges get() = _ranges

    override fun addRange(range: DateRange) : List<DateRange> {
        return if (_ranges.isEmpty()) {
            _ranges = listOf(range)
            _ranges
        } else {
            val diffs = diffAll(range, _ranges)
            val added = add(range, _ranges)
            val merged = mergeAll(added)
            _ranges = merged
            diffs
        }
    }

    private fun add(range : DateRange, ranges : List<DateRange>) : List<DateRange> {
        for ((index, r) in ranges.withIndex()){
            if (r.start > range.start) return ranges.toMutableList().apply { add(index, range) }
        }
        return ranges + range
    }

    private fun diffAll(range : DateRange, ranges : List<DateRange>): List<DateRange> {
        val list = mutableListOf<DateRange>()
        var latestDiff = range
        ranges.forEachIndexed { index, it ->
            val diffs = diff(latestDiff, it)
            if (diffs.isEmpty()) return@forEachIndexed
            list.addAll(diffs.dropLast(1))
            latestDiff = diffs.last()
            if (index == ranges.size - 1) list.add(latestDiff)
        }
        return list
    }

    private fun diff(range1 : DateRange, range2 : DateRange) : List<DateRange> {
        val from = range1.start in range2
        val to = range1.endInclusive in range2

        return when {
            from && to -> listOf() // smaller
            !from && to -> listOf(range1.start..range2.start.minusDays(1)) // left
            from && !to -> listOf(range2.endInclusive.plusDays(1)..range1.endInclusive) // right
            range1.start < range2.start && range1.endInclusive > range2.endInclusive -> listOf(range1.start..range2.start.minusDays(1),range2.endInclusive.plusDays(1)..range1.endInclusive) // bigger
            else -> listOf(range1)
        }
    }

    private fun mergeAll(dateRanges : List<DateRange>) : List<DateRange> {
        if (dateRanges.size <= 1) return dateRanges
        val list = mutableListOf<DateRange>()
        var latest : DateRange? = null
        for ((index, currentRange) in dateRanges.withIndex()){
            when(index) {
                0 -> { latest = currentRange }  // First range
                else -> {
                    val merged = merge(latest!!, currentRange)
                    if (merged == null){
                        // different ranges
                        list += latest
                        latest = currentRange
                    } else latest = merged

                    if (index == dateRanges.size - 1) list += latest
                }
            }
        }
        return list
    }

    private fun merge(range1 : DateRange, range2 : DateRange) : DateRange? {
        val from = range1.start in range2
        val to = range1.endInclusive in range2

        return when {
            from && to -> range2
            !from && to -> range1.start..range2.endInclusive
            from && !to -> range2.start..range1.endInclusive
            range1.start < range2.start && range1.endInclusive > range2.endInclusive -> range1
            else -> null
        }
    }
}

fun DateRangeMerger() : DateRange.Merger = MergerImpl()