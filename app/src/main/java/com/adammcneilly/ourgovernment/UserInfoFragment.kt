package com.adammcneilly.ourgovernment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import com.adammcneilly.ourgovernment.models.StateList
import com.adammcneilly.ourgovernment.rest.LocalManager
import com.adammcneilly.ourgovernment.rest.StateManager
import kotlinx.android.synthetic.main.fragment_user_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Fragment to collect user's information.
 *
 * Created by adam.mcneilly on 1/10/17.
 */
class UserInfoFragment : Fragment(), View.OnClickListener {

    val stateManager = StateManager()
    var stateList = StateList()
    var selectedState: StateList.State? = null
        set(value) {
            field = value
            state?.setText(value?.name.orEmpty())
            getCounties()
            getCities()
        }

    val localManager = LocalManager()
    var countyList = CountyList()
    var selectedCounty: CountyList.County? = null
        set(value) {
            field = value
            county?.setText(value?.name.orEmpty())
        }

    var cityList = CityList()
    var selectedCity: CityList.City? = null
        set(value) {
            field = value
            city?.setText(value?.name.orEmpty())
        }

    var state: EditText? = null
    var county: EditText? = null
    var city: EditText? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_user_info, container, false)

        state = view?.findViewById(R.id.state) as EditText
        state?.setOnClickListener(this)

        county = view?.findViewById(R.id.county) as EditText
        county?.setOnClickListener(this)

        city = view?.findViewById(R.id.city) as EditText
        city?.setOnClickListener(this)

        val submit = view?.findViewById(R.id.submit) as? Button
        submit?.setOnClickListener(this)

        getStates()

        return view
    }

    private fun getStates() {
        //TODO:
        val stateCall = stateManager.getStateIDs()
        stateCall.enqueue(object : Callback<StateList> {
            override fun onResponse(call: Call<StateList>?, response: Response<StateList>?) {
                if (response?.isSuccessful.orFalse()) {
                    stateList = response!!.body()
                    stateList.sortBy { it.name }
                    selectedState = stateList.firstOrNull()
                    state?.isEnabled = true
                }
            }

            override fun onFailure(call: Call<StateList>?, t: Throwable?) {
                Log.e("UserInfoFragment", t?.message, t)
            }
        })
    }

    private fun getCounties() {
        val countyCall = localManager.getCounties(selectedState?.stateId.orEmpty())
        countyCall.enqueue(object : Callback<CountyList> {
            override fun onFailure(call: Call<CountyList>?, t: Throwable?) {
                Log.e("UserInfoFragment", t?.message, t)
            }

            override fun onResponse(call: Call<CountyList>?, response: Response<CountyList>?) {
                if (response?.isSuccessful.orFalse()) {
                    countyList = response!!.body()
                    countyList.sortBy { it.name }
                    selectedCounty = countyList.firstOrNull()
                    county?.isEnabled = true
                }
            }
        })
    }

    private fun getCities() {
        val countyCall = localManager.getCities(selectedState?.stateId.orEmpty())
        countyCall.enqueue(object : Callback<CityList> {
            override fun onFailure(call: Call<CityList>?, t: Throwable?) {
                Log.e("UserInfoFragment", t?.message, t)
            }

            override fun onResponse(call: Call<CityList>?, response: Response<CityList>?) {
                if (response?.isSuccessful.orFalse()) {
                    cityList = response!!.body()
                    cityList.sortBy { it.name }
                    selectedCity = cityList.firstOrNull()
                    city?.isEnabled = true
                }
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
                    Toast.makeText(context, "Everything is valid!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true

        if (selectedState == null) {
            isValid = false
        }

        if (zip_code.length() != 5 && zip_code.length() != 9) {
            isValid = false
            zip_code.error = "Must enter a 5 or 9 digit zip code."
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