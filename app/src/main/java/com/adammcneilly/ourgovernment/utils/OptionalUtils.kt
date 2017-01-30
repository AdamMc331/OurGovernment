package com.adammcneilly.ourgovernment.utils

/**
 * Utility methods for certain optional types.
 *
 * Created by adam.mcneilly on 1/10/17.
 */

/**
 * Converts an optional boolean to false if null.
 */
fun Boolean?.orFalse(): Boolean {
    return (this ?: false)
}

/**
 * Converts an optional boolean to true if null.
 */
fun Boolean?.orTrue(): Boolean {
    return (this ?: true)
}