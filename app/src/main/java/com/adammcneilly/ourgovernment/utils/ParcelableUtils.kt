package com.adammcneilly.ourgovernment.utils

import android.os.Parcel
import android.os.Parcelable

/**
 * Utility methods for parcelable classes.
 *
 * Created by adam.mcneilly on 1/17/17.
 */
inline fun <reified T : Parcelable> creator(crossinline create: (Parcel) -> T) = object : Parcelable.Creator<T> {
    override fun createFromParcel(source: Parcel) = create(source)
    override fun newArray(size: Int) = arrayOfNulls<T?>(size)
}