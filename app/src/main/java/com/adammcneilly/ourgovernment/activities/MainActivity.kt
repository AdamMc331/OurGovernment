package com.adammcneilly.ourgovernment.activities

import android.content.Intent
import android.os.Bundle
import com.adammcneilly.ourgovernment.R
import com.adammcneilly.ourgovernment.fragments.UserInfoFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (user != null) {
            val intent = Intent(this, DrawerActivity::class.java)
            startActivity(intent)
            finish()
        }

        val userInfo = UserInfoFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, userInfo).commit()
    }
}