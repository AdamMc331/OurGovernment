package com.adammcneilly.ourgovernment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adammcneilly.ourgovernment.rest.VSApi

class MainActivity : AppCompatActivity() {

    private val api = VSApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
