package com.adammcneilly.ourgovernment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInfo = UserInfoFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, userInfo).commit()
    }
}