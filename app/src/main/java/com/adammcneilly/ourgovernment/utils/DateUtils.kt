package com.adammcneilly.ourgovernment.utils

import java.util.*

/**
 * Utility methods for a Date.
 *
 * Created by adam.mcneilly on 1/5/17.
 */

/**
 * Converts a Date object to a Calendar.
 */
fun Date.asCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

/**
 * Returns the given property of a date.
 */
fun Date.get(field: Int): Int {
    return this.asCalendar().get(field)
}

// Convenience methods
fun Date.year(): Int {
    return this.get(Calendar.YEAR)
}

fun Date.month(): Int {
    return this.get(Calendar.MONTH)
}

fun Date.day(): Int {
    return this.get(Calendar.DAY_OF_MONTH)
}