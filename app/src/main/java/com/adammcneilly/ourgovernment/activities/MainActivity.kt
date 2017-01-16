package com.adammcneilly.ourgovernment.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adammcneilly.ourgovernment.R
import com.adammcneilly.ourgovernment.fragments.UserInfoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInfo = UserInfoFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, userInfo).commit()
    }
}