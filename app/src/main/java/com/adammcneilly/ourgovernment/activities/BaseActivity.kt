package com.adammcneilly.ourgovernment.activities

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adammcneilly.ourgovernment.models.User
import com.google.gson.Gson

/**
 * Base activity that performs functions any activity will use.
 *
 * Created by adam.mcneilly on 1/17/17.
 */
open class BaseActivity: AppCompatActivity() {

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val userString = prefs.getString(USER, "")

        if (userString.isNotEmpty()) {
            user = Gson().fromJson(userString, User::class.java)
        }
    }

    override fun onPause() {
        super.onPause()

        saveUser()
    }

    /**
     * Saves a JSON string representation of the user to shared preferences.
     */
    fun saveUser() {
        val editor = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit()
        if (user != null) {
            val userString = Gson().toJson(user)
            editor.putString(USER, userString)
        }
        editor.apply()
    }

    companion object {
        val PREF_NAME = "OurGovernment"
        val USER = "User"
    }
}