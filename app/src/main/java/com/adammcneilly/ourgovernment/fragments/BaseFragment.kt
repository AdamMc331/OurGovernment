package com.adammcneilly.ourgovernment.fragments

import android.support.v4.app.Fragment
import com.adammcneilly.ourgovernment.activities.BaseActivity

/**
 * Base fragment methods
 *
 * Created by adam.mcneilly on 1/17/17.
 */
open class BaseFragment: Fragment() {
    val baseActivity: BaseActivity
        get() = activity as BaseActivity
}