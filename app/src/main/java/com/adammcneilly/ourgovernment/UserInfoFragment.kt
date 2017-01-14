package com.adammcneilly.ourgovernment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import com.adammcneilly.ourgovernment.models.StateList
import com.adammcneilly.ourgovernment.rest.LocalManager
import com.adammcneilly.ourgovernment.rest.StateManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * Fragment to collect user's information.
 *
 * Created by adam.mcneilly on 1/10/17.
 */
class UserInfoFragment : Fragment(), View.OnClickListener {

    // Managers
    val stateManager = StateManager()
    val localManager = LocalManager()

    // Proxy lists
    var stateList = StateList()
        set(value) {
            field = value
            stateList.sortBy { it.name }
            selectedState = stateList.firstOrNull()
            state?.isEnabled = true
        }
    var countyList = CountyList()
        set(value) {
            field = value
            countyList.sortBy { it.name }
            selectedCounty = countyList.firstOrNull()
            county?.isEnabled = true
        }
    var cityList = CityList()
        set(value) {
            field = value
            cityList.sortBy { it.name }
            selectedCity = cityList.firstOrNull()
            city?.isEnabled = true
        }

    // Selected values
    var selectedState: StateList.State? = null
        set(value) {
            field = value
            state?.setText(value?.name.orEmpty())
            getCounties()
            getCities()
        }
    var selectedCounty: CountyList.County? = null
        set(value) {
            field = value
            county?.setText(value?.name.orEmpty())
        }
    var selectedCity: CityList.City? = null
        set(value) {
            field = value
            city?.setText(value?.name.orEmpty())
        }

    // UI elements
    var state: EditText? = null
    var county: EditText? = null
    var city: EditText? = null
    var zipCode: EditText? = null
    var progressBar: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_user_info, container, false)

        state = view?.findViewById(R.id.state) as? EditText
        state?.setOnClickListener(this)

        county = view?.findViewById(R.id.county) as? EditText
        county?.setOnClickListener(this)

        city = view?.findViewById(R.id.city) as? EditText
        city?.setOnClickListener(this)

        zipCode = view?.findViewById(R.id.zip_code) as? EditText

        progressBar = view?.findViewById(R.id.progress_bar) as? ProgressBar

        val submit = view?.findViewById(R.id.submit) as? Button
        submit?.setOnClickListener(this)

        getStates()

        return view
    }

    private fun getStates() {
        progressBar?.visibility = View.VISIBLE

        stateManager.getStateIDs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<StateList>() {
                    override fun onCompleted() {
                        progressBar?.visibility = View.GONE
                    }

                    override fun onNext(t: StateList?) {
                        if (t != null) stateList = t
                    }

                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                    }
                })
    }

    private fun getCounties() {
        progressBar?.visibility = View.VISIBLE

        localManager.getCounties(selectedState?.stateId.orEmpty())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CountyList>() {
                    override fun onCompleted() {
                        progressBar?.visibility = View.GONE
                    }

                    override fun onNext(t: CountyList?) {
                        if (t != null) countyList = t
                    }

                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                    }
                })
    }

    private fun getCities() {
        progressBar?.visibility = View.VISIBLE

        localManager.getCities(selectedState?.stateId.orEmpty())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CityList>() {
                    override fun onCompleted() {
                        progressBar?.visibility = View.GONE
                    }

                    override fun onNext(t: CityList?) {
                        if (t != null) cityList = t
                    }

                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                    }
                })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.state -> {
                val adapter = ArrayAdapter<StateList.State>(context, R.layout.text_view, stateList)
                AlertDialog.Builder(context)
                        .setAdapter(adapter, { dialog, which -> selectedState = adapter.getItem(which) })
                        .setTitle("Select State")
                        .create()
                        .show()
            }
            R.id.county -> {
                val adapter = ArrayAdapter<CountyList.County>(context, R.layout.text_view, countyList)
                AlertDialog.Builder(context)
                        .setAdapter(adapter, { dialog, which -> selectedCounty = adapter.getItem(which) })
                        .setTitle("Select County")
                        .create()
                        .show()
            }
            R.id.city -> {
                val adapter = ArrayAdapter<CityList.City>(context, R.layout.text_view, cityList)
                AlertDialog.Builder(context)
                        .setAdapter(adapter, { dialog, which -> selectedCity = adapter.getItem(which) })
                        .setTitle("Select City")
                        .create()
                        .show()
            }
            R.id.submit -> {
                if (validateInput()) {
                    val editor = context.getSharedPreferences(Constants.SharedPreferences.PREF_NAME, Context.MODE_PRIVATE).edit()
                    editor.putString(Constants.SharedPreferences.USER_STATE, selectedState?.stateId)
                    editor.putString(Constants.SharedPreferences.USER_COUNTY, selectedCounty?.localId)
                    editor.putString(Constants.SharedPreferences.USER_CITY, selectedCity?.localId)
                    editor.putString(Constants.SharedPreferences.USER_ZIP, zipCode?.text.toString())
                    editor.apply()

                    val drawerIntent = Intent(context, DrawerActivity::class.java)
                    startActivity(drawerIntent)
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true

        if (selectedState == null) { isValid = false }

        if (zipCode?.length() != 5 && zipCode?.length() != 9) {
            isValid = false
            zipCode?.error = "Must enter a 5 or 9 digit zip code."
        }

        return isValid
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