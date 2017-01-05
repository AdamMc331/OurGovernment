package com.adammcneilly.ourgovernment

import java.util.*

/**
 * Utility methods for a Date.
 *
 * Created by adam.mcneilly on 1/5/17.
 */
fun Date.asCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

fun Date.year(): Int {
    return this.asCalendar().get(Calendar.YEAR)
}