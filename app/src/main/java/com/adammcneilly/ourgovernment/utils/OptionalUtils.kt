package com.adammcneilly.ourgovernment.utils

/**
 * Utility methods for certain optional types.
 *
 * Created by adam.mcneilly on 1/10/17.
 */
fun Boolean?.orFalse(): Boolean {
    return (this ?: false)
}

fun Boolean?.orTrue(): Boolean {
    return (this ?: true)
}