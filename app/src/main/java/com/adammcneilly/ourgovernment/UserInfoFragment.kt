package com.adammcneilly.ourgovernment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Fragment to collect user's information.
 *
 * Created by adam.mcneilly on 1/10/17.
 */
class UserInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_user_info, container, false)

        //TODO:

        return view
    }

    companion object {
        fun newInstance(): UserInfoFragment {
            val args = Bundle()

            val fragment = UserInfoFragment()
            fragment.arguments = args

            return fragment
        }
    }
}