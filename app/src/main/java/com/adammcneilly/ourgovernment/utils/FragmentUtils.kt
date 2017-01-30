package com.adammcneilly.ourgovernment.utils

import android.os.Bundle
import com.adammcneilly.ourgovernment.fragments.BaseFragment

/**
 * Utility methods for fragments
 *
 * Created by adam.mcneilly on 1/17/17.
 */

/**
 * Creates an instance of a fragment with empty arguments.
 */
inline fun <T : BaseFragment> newFragment(crossinline create: () -> T): T {
    return newFragment(create, Bundle())
}

/**
 * Creates an instance of a fragment with arguments passed in.
 */
inline fun <T : BaseFragment> newFragment(crossinline create: () -> T, args: Bundle): T {
    val fragment = create()
    fragment.arguments = args
    return fragment
}