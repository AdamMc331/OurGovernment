package com.adammcneilly.ourgovernment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.rest.VSApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val api = VSApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit.setOnClickListener {
            val call = api.getByZip(zip_code.text.toString())
            call.enqueue(object : Callback<CandidateList> {
                override fun onResponse(call: Call<CandidateList>?, response: Response<CandidateList>?) {
                    //TODO:
                }

                override fun onFailure(call: Call<CandidateList>?, t: Throwable?) {
                    Log.e("MainActivity", t?.message, t)
                }
            })
        }
    }
}