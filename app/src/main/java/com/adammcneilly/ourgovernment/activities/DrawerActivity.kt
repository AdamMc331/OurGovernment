package com.adammcneilly.ourgovernment.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.adammcneilly.ourgovernment.Constants
import com.adammcneilly.ourgovernment.fragments.LocalOfficialsFragment
import com.adammcneilly.ourgovernment.R
import com.adammcneilly.ourgovernment.fragments.StateOfficialsFragment
import com.adammcneilly.ourgovernment.models.User
import com.google.gson.Gson

class DrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        val user = getUser()

        var fragment: Fragment? = null

        when (id) {
            R.id.nav_county_officials -> fragment = LocalOfficialsFragment.newInstance(user.county?.localId, LocalOfficialsFragment.LocalType.COUNTY)
            R.id.nav_city_officials -> fragment = LocalOfficialsFragment.newInstance(user.city?.localId, LocalOfficialsFragment.LocalType.CITY)
            R.id.nav_state_officials -> fragment = StateOfficialsFragment.newInstance(user.state?.stateId)
            R.id.nav_share -> { }
            R.id.nav_send -> { }
        }

        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getUser(): User {
        val prefs = getSharedPreferences(Constants.SharedPreferences.PREF_NAME, Context.MODE_PRIVATE)
        val userString = prefs.getString(Constants.SharedPreferences.USER, "")
        val user = Gson().fromJson(userString, User::class.java)
        return user
    }
}
